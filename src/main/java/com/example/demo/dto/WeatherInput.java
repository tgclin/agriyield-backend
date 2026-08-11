package com.example.demo.dto;

public class WeatherInput {

    private double temperature;
    private double rainfall;

    public WeatherInput() {
    }

    public WeatherInput(double temperature, double rainfall) {
        this.temperature = temperature;
        this.rainfall = rainfall;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }
}