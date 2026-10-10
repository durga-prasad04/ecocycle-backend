package com.ecocycle.recommendation;

import com.ecocycle.impact.EnvironmentalImpact;

public class RecommendationResult {

    private String recommendation;

    private double repairScore;
    private double reuseScore;
    private double donateScore;
    private double sellScore;
    private double recycleScore;

    private String reason;

    private EnvironmentalImpact environmentalImpact;

    public RecommendationResult() {
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public double getRepairScore() {
        return repairScore;
    }

    public void setRepairScore(double repairScore) {
        this.repairScore = repairScore;
    }

    public double getReuseScore() {
        return reuseScore;
    }

    public void setReuseScore(double reuseScore) {
        this.reuseScore = reuseScore;
    }

    public double getDonateScore() {
        return donateScore;
    }

    public void setDonateScore(double donateScore) {
        this.donateScore = donateScore;
    }

    public double getSellScore() {
        return sellScore;
    }

    public void setSellScore(double sellScore) {
        this.sellScore = sellScore;
    }

    public double getRecycleScore() {
        return recycleScore;
    }

    public void setRecycleScore(double recycleScore) {
        this.recycleScore = recycleScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EnvironmentalImpact getEnvironmentalImpact() {
        return environmentalImpact;
    }

    public void setEnvironmentalImpact(EnvironmentalImpact environmentalImpact) {
        this.environmentalImpact = environmentalImpact;
    }
}