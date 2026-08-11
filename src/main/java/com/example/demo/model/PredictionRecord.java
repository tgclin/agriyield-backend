package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "prediction_records")
public class PredictionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "crop_type", nullable = false)
    private String cropType;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    private double temperature;
    private double rainfall;

    @Column(name = "predicted_yield", nullable = false)
    private double predictedYield;

    @ElementCollection
    @CollectionTable(name = "prediction_insights", joinColumns = @JoinColumn(name = "prediction_id"))
    @Column(name = "insight")
    private List<String> insights;

    @ElementCollection
    @CollectionTable(name = "prediction_recommendations", joinColumns = @JoinColumn(name = "prediction_id"))
    @Column(name = "recommendation")
    private List<String> recommendations;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public PredictionRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getRainfall() { return rainfall; }
    public void setRainfall(double rainfall) { this.rainfall = rainfall; }

    public double getPredictedYield() { return predictedYield; }
    public void setPredictedYield(double predictedYield) { this.predictedYield = predictedYield; }

    public List<String> getInsights() { return insights; }
    public void setInsights(List<String> insights) { this.insights = insights; }

    public List<String> getRecommendations() { return recommendations; }
    public void setRecommendations(List<String> recommendations) { this.recommendations = recommendations; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}