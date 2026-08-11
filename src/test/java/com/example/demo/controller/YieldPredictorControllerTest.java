package com.example.demo.controller;

import com.example.demo.dto.PredictionRequest;
import com.example.demo.dto.YieldAnalysisResult;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.service.YieldCalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(YieldPredictorController.class)
@Import(GlobalExceptionHandler.class)
class YieldPredictorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private YieldCalculationService yieldCalculationService;

    @Test
    @DisplayName("POST /api/yield/predict - Success Scenario")
    void predictYield_ValidRequest_Returns200() throws Exception {
        PredictionRequest request = new PredictionRequest();
        request.setLatitude(6.6745);
        request.setLongitude(-1.5716);
        request.setCropType("Maize");

        YieldAnalysisResult mockResult = new YieldAnalysisResult();

        Principal mockPrincipal = new UsernamePasswordAuthenticationToken("test@example.com", "password");

        when(yieldCalculationService.calculatePrediction(any(PredictionRequest.class), eq("test@example.com")))
                .thenReturn(mockResult);

        mockMvc.perform(post("/api/yield/predict")
                        .principal(mockPrincipal)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/yield/predict - Validation Error Scenario")
    void predictYield_InvalidRequest_Returns400() throws Exception {
        PredictionRequest invalidRequest = new PredictionRequest();
        invalidRequest.setLatitude(100.0); // Invalid latitude triggers validation error
        invalidRequest.setLongitude(-1.5716);
        invalidRequest.setCropType("");

        Principal mockPrincipal = new UsernamePasswordAuthenticationToken("test@example.com", "password");

        mockMvc.perform(post("/api/yield/predict")
                        .principal(mockPrincipal)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}