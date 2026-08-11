package com.example.demo.repository;

import com.example.demo.model.SavedFarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedFarmRepository extends JpaRepository<SavedFarm, Long> {
    List<SavedFarm> findByUserId(Long userId);
    List<SavedFarm> findByUserEmail(String userEmail);
}