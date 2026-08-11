package com.example.demo.service;

import com.example.demo.dto.AnomalyAlert;
import com.example.demo.dto.WeatherSummaryDto;
import com.example.demo.model.RegionalClimate;
import com.example.demo.repository.RegionalClimateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAnalyticsService {

    private final WeatherService weatherService;
    private final RegionalClimateRepository regionalClimateRepository;

    public WeatherAnalyticsService(WeatherService weatherService, RegionalClimateRepository regionalClimateRepository) {
        this.weatherService = weatherService;
        this.regionalClimateRepository = regionalClimateRepository;
    }

    public WeatherSummaryDto getWeatherSummaryAndAnomalies(double latitude, double longitude) {
        var weatherInput = weatherService.getWeatherData(latitude, longitude);
        double liveTemp = weatherInput.getTemperature();

        RegionalClimate region = regionalClimateRepository.findByCoordinates(latitude, longitude)
                .orElse(new RegionalClimate("Default Zone", 0, 0, 0, 0, 1100.0));

        double historicalRainfall = region.getSeasonalRainfallMm();
        List<AnomalyAlert> alerts = new ArrayList<>();

        // Temperature Anomaly Check
        if (liveTemp > 35.0) {
            alerts.add(new AnomalyAlert(
                    "HEAT_SPIKE",
                    "HIGH",
                    String.format("Current temperature (%.1f°C) is significantly above historical averages.", liveTemp),
                    "Apply shade structures and execute early-morning micro-irrigation."
            ));
        }

        // Rainfall Anomaly Check
        if (historicalRainfall < 600.0) {
            alerts.add(new AnomalyAlert(
                    "DROUGHT_RISK",
                    "MEDIUM",
                    String.format("Seasonal rainfall baseline (%.1f mm) is lower than crop safety thresholds.", historicalRainfall),
                    "Deploy drip irrigation systems and apply organic mulch to conserve soil moisture."
            ));
        }

        return new WeatherSummaryDto(
                liveTemp,
                65.0, // Default relative humidity estimate
                12.5, // Default wind speed (km/h)
                historicalRainfall,
                alerts
        );
    }
}