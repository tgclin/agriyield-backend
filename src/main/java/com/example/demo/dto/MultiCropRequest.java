package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MultiCropRequest {

    @NotNull(message = "Crop types list cannot be null")
    @NotEmpty(message = "Crop types list must contain at least one crop")
    private List<String> cropTypes;

    // Default constructor (required by Jackson/Spring)
    public MultiCropRequest() {}

    // Constructor matching parameters
    public MultiCropRequest(List<String> cropTypes) {
        this.cropTypes = cropTypes;
    }

    // Getters and Setters
    public List<String> getCropTypes() {
        return cropTypes;
    }

    public void setCropTypes(List<String> cropTypes) {
        this.cropTypes = cropTypes;
    }
}