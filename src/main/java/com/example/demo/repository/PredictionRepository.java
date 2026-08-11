package com.example.demo.repository;

import com.example.demo.model.PredictionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PredictionRepository extends JpaRepository<PredictionRecord, Long> {

    List<PredictionRecord> findAllByOrderByIdDesc();

    List<PredictionRecord> findByCropTypeIgnoreCase(String cropType);

    // Added to filter prediction history by user email
    List<PredictionRecord> findByUserEmailOrderByIdDesc(String userEmail);
}