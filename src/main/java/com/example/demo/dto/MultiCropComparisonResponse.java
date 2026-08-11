package com.example.demo.dto;

import java.util.List;

public class MultiCropComparisonResponse {
    private List<YieldAnalysisResult> cropRankings;

    // Default constructor
    public MultiCropComparisonResponse() {}

    // Constructor that accepts List<YieldAnalysisResult> (Fixes the underlined "rankings")
    public MultiCropComparisonResponse(List<YieldAnalysisResult> cropRankings) {
        this.cropRankings = cropRankings;
    }

    // Getters and Setters
    public List<YieldAnalysisResult> getCropRankings() {
        return cropRankings;
    }

    public void setCropRankings(List<YieldAnalysisResult> cropRankings) {
        this.cropRankings = cropRankings;
    }
}