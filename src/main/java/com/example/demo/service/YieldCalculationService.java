package com.example.demo.service;

import com.example.demo.dto.PredictionRequest;
import com.example.demo.dto.WeatherInput;
import com.example.demo.dto.YieldAnalysisResult;
import com.example.demo.model.CropBaseline;
import com.example.demo.model.PredictionRecord;
import com.example.demo.model.RegionalClimate;
import com.example.demo.repository.CropBaselineRepository;
import com.example.demo.repository.PredictionRepository;
import com.example.demo.repository.RegionalClimateRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class YieldCalculationService {

    private final WeatherService weatherService;
    private final PredictionRepository predictionRepository;
    private final CropBaselineRepository cropBaselineRepository;
    private final RegionalClimateRepository regionalClimateRepository;

    public YieldCalculationService(WeatherService weatherService,
                                   PredictionRepository predictionRepository,
                                   CropBaselineRepository cropBaselineRepository,
                                   RegionalClimateRepository regionalClimateRepository) {
        this.weatherService = weatherService;
        this.predictionRepository = predictionRepository;
        this.cropBaselineRepository = cropBaselineRepository;
        this.regionalClimateRepository = regionalClimateRepository;
    }

    @Cacheable("cropNames")
    public List<String> getAllCropNames() {
        try {
            List<CropBaseline> crops = cropBaselineRepository.findAllByOrderByNameAsc();
            if (crops == null || crops.isEmpty()) {
                return List.of("Maize", "Avocado", "Rice", "Cassava", "Yam", "Cocoa", "Soybean", "Tomato");
            }
            return crops.stream()
                    .filter(c -> c != null && c.getName() != null && !c.getName().isBlank())
                    .map(CropBaseline::getName)
                    .toList();
        } catch (Exception e) {
            System.err.println("Database fetch exception in getAllCropNames: " + e.getMessage());
            return List.of("Maize", "Avocado", "Rice", "Cassava", "Yam", "Cocoa", "Soybean", "Tomato");
        }
    }

    // Cached helper method for spatial region lookups
    @Cacheable(value = "regionalClimate", key = "#latitude + '_' + #longitude")
    public RegionalClimate getRegionalClimate(double latitude, double longitude) {
        return regionalClimateRepository.findByCoordinates(latitude, longitude)
                .orElse(null);
    }

    @Transactional
    public YieldAnalysisResult calculatePrediction(PredictionRequest request, String userEmail) {
        String cropType = request.getCropType();
        double latitude = request.getLatitude();
        double longitude = request.getLongitude();

        WeatherInput liveWeather = weatherService.getWeatherData(latitude, longitude);
        double liveTemp = liveWeather.getTemperature();

        // 1. Safe spatial zone lookup using cached method
        RegionalClimate region = getRegionalClimate(latitude, longitude);

        double seasonalRainfall;
        List<String> insights = new ArrayList<>();
        insights.add(String.format("Live temperature at location is %.1f°C.", liveTemp));

        if (region != null) {
            seasonalRainfall = region.getSeasonalRainfallMm();
            insights.add(String.format("Matched regional agro-zone: %s (Baseline rainfall: %.1f mm).",
                    region.getRegionName(), seasonalRainfall));
        } else {
            seasonalRainfall = 1100.0; // Fallback baseline national average
            insights.add("Coordinates outside mapped regional agro-zones. Applied national baseline average (1100.0 mm rainfall).");
        }

        // 2. Calculate yield output
        double predictedYield = calculateYieldForCrop(cropType, liveTemp, seasonalRainfall);

        List<String> recommendations = new ArrayList<>();
        recommendations.add("Apply balanced soil amendments according to crop growth phase.");
        recommendations.add("Monitor field moisture levels during peak development stage.");

        CropBaseline crop = cropBaselineRepository.findByNameIgnoreCase(cropType.trim()).orElse(null);
        if (crop != null && liveTemp > crop.getOptimalTempMax()) {
            recommendations.add(String.format("Thermal stress alert: Current temperature (%.1f°C) exceeds optimal max (%.1f°C). Irrigation suggested.",
                    liveTemp, crop.getOptimalTempMax()));
        }

        // 3. Save prediction record to database
        PredictionRecord record = new PredictionRecord();
        record.setUserEmail(userEmail);
        record.setCropType(cropType);
        record.setLatitude(latitude);
        record.setLongitude(longitude);
        record.setTemperature(liveTemp);
        record.setRainfall(seasonalRainfall);
        record.setPredictedYield(predictedYield);
        record.setInsights(insights);
        record.setRecommendations(recommendations);
        predictionRepository.save(record);

        return new YieldAnalysisResult(
                cropType,
                predictedYield,
                "Hybrid Model: Live OpenWeatherMap thermal data + PostgreSQL regional climate seasonal rainfall.",
                insights,
                recommendations
        );
    }

    @Transactional
    public YieldAnalysisResult calculatePrediction(PredictionRequest request) {
        return calculatePrediction(request, null);
    }

    @Cacheable(value = "cropCalculations", key = "#cropName + '_' + #temperature + '_' + #rainfall")
    public double calculateYieldForCrop(String cropName, double temperature, double rainfall) {
        CropBaseline crop = cropBaselineRepository.findByNameIgnoreCase(cropName.trim())
                .orElse(new CropBaseline(cropName, "General", 2.5, 18.0, 32.0, 400.0, 1000.0));

        double baseYield = crop.getBaseYield();
        double yieldMultiplier = 1.0;

        if (temperature < crop.getOptimalTempMin()) {
            yieldMultiplier -= 0.15 * ((crop.getOptimalTempMin() - temperature) / 5.0);
        } else if (temperature > crop.getOptimalTempMax()) {
            yieldMultiplier -= 0.20 * ((temperature - crop.getOptimalTempMax()) / 5.0);
        }

        if (rainfall < crop.getOptimalRainfallMin()) {
            yieldMultiplier -= 0.25 * ((crop.getOptimalRainfallMin() - rainfall) / 200.0);
        } else if (rainfall > crop.getOptimalRainfallMax()) {
            yieldMultiplier -= 0.10 * ((rainfall - crop.getOptimalRainfallMax()) / 300.0);
        }

        yieldMultiplier = Math.max(0.20, yieldMultiplier);
        return Math.round(baseYield * yieldMultiplier * 100.0) / 100.0;
    }

    public List<PredictionRecord> getPredictionHistory() {
        return predictionRepository.findAllByOrderByIdDesc();
    }

    public List<PredictionRecord> getPredictionsByUserEmail(String email) {
        return predictionRepository.findByUserEmailOrderByIdDesc(email);
    }

    @Transactional
    public void deletePrediction(Long id) {
        if (id != null) {
            predictionRepository.deleteById(id);
        }
    }

    @Transactional
    public void deletePredictionById(Long id) {
        deletePrediction(id);
    }
}