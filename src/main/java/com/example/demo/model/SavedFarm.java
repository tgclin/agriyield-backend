package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_farms")
public class SavedFarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    private Long userId;
    private String farmName;
    private double latitude;
    private double longitude;
    private String preferredCrop;

    public SavedFarm() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFarmName() { return farmName; }
    public void setFarmName(String farmName) { this.farmName = farmName; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getPreferredCrop() { return preferredCrop; }
    public void setPreferredCrop(String preferredCrop) { this.preferredCrop = preferredCrop; }
}