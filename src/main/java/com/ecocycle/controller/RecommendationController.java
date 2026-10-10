package com.ecocycle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.recommendation.RecommendationEngine;
import com.ecocycle.recommendation.RecommendationResult;
import com.ecocycle.service.DeviceService;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationEngine recommendationEngine;
    private final DeviceService deviceService;

    public RecommendationController(
            RecommendationEngine recommendationEngine,
            DeviceService deviceService) {

        this.recommendationEngine = recommendationEngine;
        this.deviceService = deviceService;
    }

    // Get recommendation for a device
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<RecommendationResult> getRecommendation(
            @PathVariable Long deviceId) {

        return deviceService.getDeviceById(deviceId)
                .map(device -> {

                    RecommendationResult result =
                            recommendationEngine.evaluate(device);

                    return ResponseEntity.ok(result);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}