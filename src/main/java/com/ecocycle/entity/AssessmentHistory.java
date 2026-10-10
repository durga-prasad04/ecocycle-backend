package com.ecocycle.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "assessment_history")
public class AssessmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String recommendation;

    private Double repairScore;

    private Double reuseScore;

    private Double donateScore;

    private Double sellScore;

    private Double recycleScore;

    @Column(length = 1000)
    private String reason;

    private Double eWasteDiverted;

    private Double usefulLifeExtension;

    @Column(nullable = false, updatable = false)
    private LocalDateTime assessedAt;

    @PrePersist
    protected void onCreate() {
        assessedAt = LocalDateTime.now();
    }

    public AssessmentHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Double getRepairScore() {
        return repairScore;
    }

    public void setRepairScore(Double repairScore) {
        this.repairScore = repairScore;
    }

    public Double getReuseScore() {
        return reuseScore;
    }

    public void setReuseScore(Double reuseScore) {
        this.reuseScore = reuseScore;
    }

    public Double getDonateScore() {
        return donateScore;
    }

    public void setDonateScore(Double donateScore) {
        this.donateScore = donateScore;
    }

    public Double getSellScore() {
        return sellScore;
    }

    public void setSellScore(Double sellScore) {
        this.sellScore = sellScore;
    }

    public Double getRecycleScore() {
        return recycleScore;
    }

    public void setRecycleScore(Double recycleScore) {
        this.recycleScore = recycleScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("eWasteDiverted")
    public Double geteWasteDiverted() {
        return eWasteDiverted;
    }

    @JsonProperty("eWasteDiverted")
    public void seteWasteDiverted(Double eWasteDiverted) {
        this.eWasteDiverted = eWasteDiverted;
    }

    public Double getUsefulLifeExtension() {
        return usefulLifeExtension;
    }

    public void setUsefulLifeExtension(Double usefulLifeExtension) {
        this.usefulLifeExtension = usefulLifeExtension;
    }

    public LocalDateTime getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(LocalDateTime assessedAt) {
        this.assessedAt = assessedAt;
    }
}