package com.example.demo.controller;

import com.example.demo.dto.PredictionRequest;
import com.example.demo.dto.YieldAnalysisResult;
import com.example.demo.model.PredictionRecord;
import com.example.demo.service.YieldCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Tag(name = "Yield Predictor API", description = "Endpoints for running yield models, viewing history, and fetching crops.")
public class YieldPredictorController {

    private final YieldCalculationService yieldCalculationService;

    public YieldPredictorController(YieldCalculationService yieldCalculationService) {
        this.yieldCalculationService = yieldCalculationService;
    }

    // 1. ENDPOINT ACCESSED BY FRONTEND AT /api/crops TO POPULATE DROPDOWN
    @GetMapping("/crops")
    @Operation(summary = "Get all crop names", description = "Returns alphabetically sorted crop names from the database.")
    public ResponseEntity<List<String>> getCrops() {
        List<String> crops = yieldCalculationService.getAllCropNames();
        return ResponseEntity.ok(crops);
    }

    // 2. YIELD PREDICTION
    @PostMapping("/predictions/predict")
    @Operation(summary = "Calculate yield prediction", description = "Generates crop yield estimates based on coordinates and crop type.")
    public ResponseEntity<YieldAnalysisResult> predictYield(
            @Valid @RequestBody PredictionRequest request,
            @RequestParam(required = false) String email) {

        YieldAnalysisResult response = yieldCalculationService.calculatePrediction(request, email);
        return ResponseEntity.ok(response);
    }

    // 3. PREDICTION HISTORY
    @GetMapping("/predictions/history")
    @Operation(summary = "Retrieve prediction history", description = "Fetches previously saved yield predictions filtered by user email.")
    public ResponseEntity<List<PredictionRecord>> getPredictionHistory(@RequestParam(required = false) String email) {
        if (email == null || email.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<PredictionRecord> history = yieldCalculationService.getPredictionsByUserEmail(email);
        return ResponseEntity.ok(history);
    }

    // 4. DELETE PREDICTION LOG
    @DeleteMapping("/predictions/{id}")
    @Operation(summary = "Delete prediction log")
    public ResponseEntity<Void> deletePrediction(@PathVariable Long id) {
        yieldCalculationService.deletePredictionById(id);
        return ResponseEntity.noContent().build();
    }
}