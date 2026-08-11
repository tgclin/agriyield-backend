package com.example.demo.repository;

import com.example.demo.model.CropBaseline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CropBaselineRepository extends JpaRepository<CropBaseline, Long> {

    // Required to fetch all crops sorted alphabetically for the frontend dropdown
    List<CropBaseline> findAllByOrderByNameAsc();

    Optional<CropBaseline> findByNameIgnoreCase(String name);
}