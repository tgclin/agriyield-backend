package com.example.demo.controller;

import com.example.demo.dto.WeatherSummaryDto;
import com.example.demo.model.SavedFarm;
import com.example.demo.repository.SavedFarmRepository;
import com.example.demo.service.ReportExportService;
import com.example.demo.service.WeatherAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Weather & Analytics API", description = "Endpoints for weather dashboards, anomalies, saved farms, and CSV reports.")
public class AnalyticsAndWeatherController {

    private final WeatherAnalyticsService weatherAnalyticsService;
    private final ReportExportService reportExportService;
    private final SavedFarmRepository savedFarmRepository;

    public AnalyticsAndWeatherController(WeatherAnalyticsService weatherAnalyticsService,
                                         ReportExportService reportExportService,
                                         SavedFarmRepository savedFarmRepository) {
        this.weatherAnalyticsService = weatherAnalyticsService;
        this.reportExportService = reportExportService;
        this.savedFarmRepository = savedFarmRepository;
    }

    @GetMapping("/analytics/weather")
    @Operation(summary = "Get weather and anomaly alerts")
    public ResponseEntity<WeatherSummaryDto> getWeatherSummary(@RequestParam double lat, @RequestParam double lon) {
        return ResponseEntity.ok(weatherAnalyticsService.getWeatherSummaryAndAnomalies(lat, lon));
    }

    @GetMapping("/analytics/export/csv")
    @Operation(summary = "Export predictions as CSV")
    public ResponseEntity<byte[]> downloadCsvReport() {
        byte[] csvData = reportExportService.generatePredictionCsvReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=yield_predictions_report.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvData);
    }

    @GetMapping("/farms")
    @Operation(summary = "List saved farms for user")
    public ResponseEntity<List<SavedFarm>> getSavedFarms(@RequestParam(required = false) String email) {
        if (email == null || email.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(savedFarmRepository.findByUserEmail(email));
    }

    @PostMapping("/farms")
    @Operation(summary = "Save farm location")
    public ResponseEntity<SavedFarm> saveFarm(@RequestBody SavedFarm farm) {
        return ResponseEntity.ok(savedFarmRepository.save(farm));
    }

    @DeleteMapping("/farms/{id}")
    @Operation(summary = "Delete saved farm")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        savedFarmRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}