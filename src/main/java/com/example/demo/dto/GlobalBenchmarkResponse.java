package com.example.demo.dto;

public class GlobalBenchmarkResponse {

    private String cropType;
    private long totalRecords;
    private double meanYield;
    private double maxYield;
    private double minYield;
    private double standardDeviation;

    public GlobalBenchmarkResponse() {}

    public GlobalBenchmarkResponse(String cropType, long totalRecords, double meanYield,
                                   double maxYield, double minYield, double standardDeviation) {
        this.cropType = cropType;
        this.totalRecords = totalRecords;
        this.meanYield = meanYield;
        this.maxYield = maxYield;
        this.minYield = minYield;
        this.standardDeviation = standardDeviation;
    }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public long getTotalRecords() { return totalRecords; }
    public void setTotalRecords(long totalRecords) { this.totalRecords = totalRecords; }

    public double getMeanYield() { return meanYield; }
    public void setMeanYield(double meanYield) { this.meanYield = meanYield; }

    public double getMaxYield() { return maxYield; }
    public void setMaxYield(double maxYield) { this.maxYield = maxYield; }

    public double getMinYield() { return minYield; }
    public void setMinYield(double minYield) { this.minYield = minYield; }

    public double getStandardDeviation() { return standardDeviation; }
    public void setStandardDeviation(double standardDeviation) { this.standardDeviation = standardDeviation; }
}