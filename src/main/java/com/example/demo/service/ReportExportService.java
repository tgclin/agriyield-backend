package com.example.demo.service;

import com.example.demo.model.PredictionRecord;
import com.example.demo.repository.PredictionRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ReportExportService {

    private final PredictionRepository predictionRepository;

    public ReportExportService(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    public byte[] generatePredictionCsvReport() {
        List<PredictionRecord> records = predictionRepository.findAllByOrderByIdDesc();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PrintWriter writer = new PrintWriter(out)) {
            writer.println("ID,Crop Type,Latitude,Longitude,Temperature (C),Rainfall (mm),Predicted Yield (t/ha),Created At");
            for (PredictionRecord record : records) {
                writer.printf("%d,%s,%.4f,%.4f,%.2f,%.2f,%.2f,%s%n",
                        record.getId(),
                        record.getCropType(),
                        record.getLatitude(),
                        record.getLongitude(),
                        record.getTemperature(),
                        record.getRainfall(),
                        record.getPredictedYield(),
                        record.getCreatedAt());
            }
            writer.flush();
        }

        return out.toByteArray();
    }
}