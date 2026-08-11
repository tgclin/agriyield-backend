package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload containing yield prediction results and status.")
public class PredictionResponse {

    @Schema(description = "Predicted yield in metric tons per hectare.", example = "4.52")
    private double predictedYield;

    @Schema(description = "Status of the prediction request.", example = "SUCCESS")
    private String status;

    public PredictionResponse() {
    }

    public PredictionResponse(double predictedYield, String status) {
        this.predictedYield = predictedYield;
        this.status = status;
    }

    public double getPredictedYield() {
        return predictedYield;
    }

    public void setPredictedYield(double predictedYield) {
        this.predictedYield = predictedYield;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}