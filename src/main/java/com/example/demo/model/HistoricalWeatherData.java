package com.example.demo.model;

public class HistoricalWeatherData {

    private double historicalAvgTemp;
    private double historicalAvgRainfall;
    private String climateZone;

    public HistoricalWeatherData(double historicalAvgTemp, double historicalAvgRainfall, String climateZone) {
        this.historicalAvgTemp = historicalAvgTemp;
        this.historicalAvgRainfall = historicalAvgRainfall;
        this.climateZone = climateZone;
   }

    public double getHistoricalAvgTemp() {
        return historicalAvgTemp;
    }

    public double getHistoricalAvgRainfall() {
        return historicalAvgRainfall;
    }

    public String getClimateZone() {
        return climateZone;
    }
}