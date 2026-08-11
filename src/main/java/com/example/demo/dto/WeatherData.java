package com.example.demo.dto;

public class WeatherData {
    private double temperature;
    private double rainfall;

    public WeatherData(double temperature, double rainfall) {
        this.temperature = temperature;
        this.rainfall = rainfall;
    }

    public double getTemperature() { return temperature; }
    public double getRainfall() { return rainfall; }
}