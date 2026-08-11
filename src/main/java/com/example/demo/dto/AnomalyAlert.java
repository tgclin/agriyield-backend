package com.example.demo.dto;

public class AnomalyAlert {
    private String type; // e.g., "HEAT_SPIKE", "DROUGHT", "EXCESS_RAINFALL"
    private String severity; // "LOW", "MEDIUM", "HIGH"
    private String message;
    private String recommendedAction;

    public AnomalyAlert() {}

    public AnomalyAlert(String type, String severity, String message, String recommendedAction) {
        this.type = type;
        this.severity = severity;
        this.message = message;
        this.recommendedAction = recommendedAction;
    }

    public String getType() { return type; }
    public String getSeverity() { return severity; }
    public String getMessage() { return message; }
    public String getRecommendedAction() { return recommendedAction; }
}