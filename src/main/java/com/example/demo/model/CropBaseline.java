package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "crop_baselines")
public class CropBaseline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(name = "base_yield", nullable = false)
    private Double baseYield;

    // Converted primitives to Wrapper Objects (Double) to safely handle nulls
    @Column(name = "optimal_temp_min")
    private Double optimalTempMin;

    @Column(name = "optimal_temp_max")
    private Double optimalTempMax;

    @Column(name = "optimal_rainfall_min")
    private Double optimalRainfallMin;

    @Column(name = "optimal_rainfall_max")
    private Double optimalRainfallMax;

    // Default constructor required by JPA
    public CropBaseline() {}

    public CropBaseline(String name, String category, Double baseYield, Double optimalTempMin, Double optimalTempMax, Double optimalRainfallMin, Double optimalRainfallMax) {
        this.name = name;
        this.category = category;
        this.baseYield = baseYield;
        this.optimalTempMin = optimalTempMin;
        this.optimalTempMax = optimalTempMax;
        this.optimalRainfallMin = optimalRainfallMin;
        this.optimalRainfallMax = optimalRainfallMax;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getBaseYield() { return baseYield != null ? baseYield : 2.5; }
    public Double getOptimalTempMin() { return optimalTempMin != null ? optimalTempMin : 18.0; }
    public Double getOptimalTempMax() { return optimalTempMax != null ? optimalTempMax : 32.0; }
    public Double getOptimalRainfallMin() { return optimalRainfallMin != null ? optimalRainfallMin : 400.0; }
    public Double getOptimalRainfallMax() { return optimalRainfallMax != null ? optimalRainfallMax : 1000.0; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setBaseYield(Double baseYield) { this.baseYield = baseYield; }
    public void setOptimalTempMin(Double optimalTempMin) { this.optimalTempMin = optimalTempMin; }
    public void setOptimalTempMax(Double optimalTempMax) { this.optimalTempMax = optimalTempMax; }
    public void setOptimalRainfallMin(Double optimalRainfallMin) { this.optimalRainfallMin = optimalRainfallMin; }
    public void setOptimalRainfallMax(Double optimalRainfallMax) { this.optimalRainfallMax = optimalRainfallMax; }
}