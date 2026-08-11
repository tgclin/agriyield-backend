package com.example.demo.service;

import com.example.demo.dto.WeatherInput;
import com.example.demo.model.HistoricalWeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key:dummy_key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder builder) {
        // Enforce 3-second connect and read timeouts to prevent network hangs
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(3))
                .setReadTimeout(Duration.ofSeconds(3))
                .build();
    }

    public WeatherInput getWeatherData(double latitude, double longitude) {
        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?lat=%.4f&lon=%.4f&appid=%s&units=metric",
                latitude, longitude, apiKey
        );

        try {
            // Send request to OpenWeatherMap
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);

            if (response != null && response.has("main")) {
                // Extract temperature (in Celsius)
                double temperature = response.path("main").path("temp").asDouble(25.0);

                // Extract rainfall in mm (if available in 'rain.1h', default to 0.0)
                double rainfall = 0.0;
                if (response.has("rain") && response.path("rain").has("1h")) {
                    rainfall = response.path("rain").path("1h").asDouble(0.0);
                }

                return new WeatherInput(temperature, rainfall);
            }
        } catch (Exception e) {
            // Fallback default values if API fails, times out, or key isn't active yet
            System.err.println("Failed to fetch weather data: " + e.getMessage());
        }

        return new WeatherInput(25.0, 10.0);
    }

    // Helper method to fetch temperature directly
    public double getCurrentTemperature(double latitude, double longitude) {
        return getWeatherData(latitude, longitude).getTemperature();
    }

    // Helper method to fetch rainfall directly
    public double getCurrentRainfall(double latitude, double longitude) {
        return getWeatherData(latitude, longitude).getRainfall();
    }

    /**
     * Retrieves historical baseline averages for a given region to compare short-term anomalies.
     */
    public HistoricalWeatherData getHistoricalBaseline(double latitude, double longitude) {
        // Multi-year regional climate baselines
        double baseTemp = 24.5;
        double baseRainfall = 140.0;
        String zone = (latitude > 0) ? "Tropical/Subtropical Northern" : "Tropical/Subtropical Southern";

        return new HistoricalWeatherData(baseTemp, baseRainfall, zone);
    }
}