package com.example.demo.repository;

import com.example.demo.model.RegionalClimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface RegionalClimateRepository extends JpaRepository<RegionalClimate, Long> {

    @Query("SELECT r FROM RegionalClimate r WHERE :lat BETWEEN r.minLat AND r.maxLat AND :lon BETWEEN r.minLon AND r.maxLon")
    Optional<RegionalClimate> findByCoordinates(@Param("lat") double lat, @Param("lon") double lon);
}