package com.example.demo.service;

import com.example.demo.dto.GlobalBenchmarkResponse;
import com.example.demo.model.PredictionRecord;
import com.example.demo.repository.PredictionRepository;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatternAnalysisService {

    private final PredictionRepository predictionRepository;

    public PatternAnalysisService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    /**
     * Calculates statistical benchmarks (Mean, Min, Max, StdDev) for a specific crop.
     */
    public GlobalBenchmarkResponse getGlobalBenchmark(String cropType) {
        List<PredictionRecord> records = predictionRepository.findByCropTypeIgnoreCase(cropType);

        if (records.isEmpty()) {
            throw new IllegalArgumentException("No statistical benchmark data available for crop type: " + cropType);
        }

        List<Double> yields = records.stream()
                .map(PredictionRecord::getPredictedYield)
                .collect(Collectors.toList());

        long count = yields.size();
        DoubleSummaryStatistics stats = yields.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();

        double mean = stats.getAverage();
        double max = stats.getMax();
        double min = stats.getMin();

        // Standard Deviation Calculation
        double variance = yields.stream()
                .mapToDouble(y -> Math.pow(y - mean, 2))
                .average()
                .orElse(0.0);
        double stdDev = Math.sqrt(variance);

        return new GlobalBenchmarkResponse(
                cropType,
                count,
                Math.round(mean * 100.0) / 100.0,
                Math.round(max * 100.0) / 100.0,
                Math.round(min * 100.0) / 100.0,
                Math.round(stdDev * 100.0) / 100.0
        );
    }
}