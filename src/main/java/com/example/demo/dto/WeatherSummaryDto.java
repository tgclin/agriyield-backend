package com.example.demo.dto;

import java.util.List;

public class WeatherSummaryDto {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double rainfall;
    private List<AnomalyAlert> anomalies;

    public WeatherSummaryDto() {}

    public WeatherSummaryDto(double temperature, double humidity, double windSpeed, double rainfall, List<AnomalyAlert> anomalies) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.rainfall = rainfall;
        this.anomalies = anomalies;
    }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public double getRainfall() { return rainfall; }
    public void setRainfall(double rainfall) { this.rainfall = rainfall; }

    public List<AnomalyAlert> getAnomalies() { return anomalies; }
    public void setAnomalies(List<AnomalyAlert> anomalies) { this.anomalies = anomalies; }
}