package com.example.demo.dto;

import java.util.List;

public class CropComparisonItem {

    private String cropType;
    private double predictedYield;
    private int rank;
    private List<String> insights;
    private List<String> recommendations;

    public CropComparisonItem(String cropType, double predictedYield, int rank, List<String> insights, List<String> recommendations) {
        this.cropType = cropType;
        this.predictedYield = predictedYield;
        this.rank = rank;
        this.insights = insights;
        this.recommendations = recommendations;
    }

    public String getCropType() { return cropType; }
    public double getPredictedYield() { return predictedYield; }
    public int getRank() { return rank; }
    public List<String> getInsights() { return insights; }
    public List<String> getRecommendations() { return recommendations; }
}