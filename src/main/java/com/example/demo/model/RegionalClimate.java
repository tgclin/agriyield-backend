package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "regional_climate")
public class RegionalClimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_name", nullable = false, unique = true)
    private String regionName;

    @Column(name = "min_lat", nullable = false)
    private double minLat;

    @Column(name = "max_lat", nullable = false)
    private double maxLat;

    @Column(name = "min_lon", nullable = false)
    private double minLon;

    @Column(name = "max_lon", nullable = false)
    private double maxLon;

    @Column(name = "seasonal_rainfall_mm", nullable = false)
    private double seasonalRainfallMm;

    public RegionalClimate() {}

    public RegionalClimate(String regionName, double minLat, double maxLat, double minLon, double maxLon, double seasonalRainfallMm) {
        this.regionName = regionName;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.seasonalRainfallMm = seasonalRainfallMm;
    }

    public Long getId() { return id; }
    public String getRegionName() { return regionName; }
    public double getSeasonalRainfallMm() { return seasonalRainfallMm; }
}