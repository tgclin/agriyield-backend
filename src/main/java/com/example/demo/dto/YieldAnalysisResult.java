package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Response payload containing yield forecasts, field insights, and recommendations.")
public class YieldAnalysisResult {

    @Schema(description = "Standardized FAO crop identifier evaluated.", example = "maize")
    private String cropType;

    @Schema(description = "Forecasted yield output in Metric Tons per Hectare (MT/ha).", example = "5.85")
    private double predictedYield;

    @Schema(description = "General observations or summary notes regarding the prediction.", example = "Optimal rainfall pattern detected.")
    private String notes;

    @Schema(description = "Key analytical insights derived from model processing.")
    private List<String> insights;

    @Schema(description = "Actionable agronomic advice to optimize harvest yield.")
    private List<String> recommendations;

    public YieldAnalysisResult() {}

    public YieldAnalysisResult(String cropType, double predictedYield, String notes) {
        this.cropType = cropType;
        this.predictedYield = predictedYield;
        this.notes = notes;
    }

    public YieldAnalysisResult(double predictedYield, List<String> insights, List<String> recommendations) {
        this.predictedYield = predictedYield;
        this.insights = insights;
        this.recommendations = recommendations;
    }

    // Full constructor matching PredictionRecord fields
    public YieldAnalysisResult(String cropType, double predictedYield, String notes, List<String> insights, List<String> recommendations) {
        this.cropType = cropType;
        this.predictedYield = predictedYield;
        this.notes = notes;
        this.insights = insights;
        this.recommendations = recommendations;
    }

    // Getters and Setters
    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public double getPredictedYield() { return predictedYield; }
    public void setPredictedYield(double predictedYield) { this.predictedYield = predictedYield; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<String> getInsights() { return insights; }
    public void setInsights(List<String> insights) { this.insights = insights; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }
}