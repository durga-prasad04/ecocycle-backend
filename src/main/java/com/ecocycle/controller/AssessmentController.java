package com.ecocycle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.dto.AssessmentRequest;
import com.ecocycle.entity.AssessmentHistory;
import com.ecocycle.entity.Device;
import com.ecocycle.entity.DeviceCategory;
import com.ecocycle.entity.User;
import com.ecocycle.recommendation.RecommendationEngine;
import com.ecocycle.recommendation.RecommendationResult;
import com.ecocycle.service.AssessmentHistoryService;
import com.ecocycle.service.DeviceCategoryService;
import com.ecocycle.service.DeviceService;
import com.ecocycle.service.UserService;

@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    private final RecommendationEngine recommendationEngine;
    private final DeviceCategoryService deviceCategoryService;
    private final UserService userService;
    private final DeviceService deviceService;
    private final AssessmentHistoryService assessmentHistoryService;

    public AssessmentController(
            RecommendationEngine recommendationEngine,
            DeviceCategoryService deviceCategoryService,
            UserService userService,
            DeviceService deviceService,
            AssessmentHistoryService assessmentHistoryService) {

        this.recommendationEngine = recommendationEngine;
        this.deviceCategoryService = deviceCategoryService;
        this.userService = userService;
        this.deviceService = deviceService;
        this.assessmentHistoryService = assessmentHistoryService;
    }

    @PostMapping
    public ResponseEntity<?> assessDevice(
            @RequestBody AssessmentRequest request) {

        DeviceCategory category =
                deviceCategoryService
                        .getCategoryById(request.getCategoryId())
                        .orElse(null);

        if (category == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Device category not found");
        }

        User user =
                userService
                        .getUserById(request.getUserId())
                        .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        Device device = new Device();

        device.setDeviceName(request.getDeviceName());
        device.setBrand(request.getBrand());
        device.setModel(request.getModel());
        device.setAgeYears(request.getAgeYears());
        device.setCondition(request.getCondition());
        device.setPurchasePrice(request.getPurchasePrice());
        device.setCurrentValue(request.getCurrentValue());
        device.setRepairCost(request.getRepairCost());
        device.setExpectedRemainingLife(
                request.getExpectedRemainingLife()
        );

        device.setCategory(category);
        device.setUser(user);

        Device savedDevice =
                deviceService.saveDevice(device);

        RecommendationResult result =
                recommendationEngine.evaluate(savedDevice);

        AssessmentHistory history =
                new AssessmentHistory();

        history.setUser(user);
        history.setDevice(savedDevice);

        history.setRecommendation(
                result.getRecommendation()
        );

        history.setRepairScore(
                result.getRepairScore()
        );

        history.setReuseScore(
                result.getReuseScore()
        );

        history.setDonateScore(
                result.getDonateScore()
        );

        history.setSellScore(
                result.getSellScore()
        );

        history.setRecycleScore(
                result.getRecycleScore()
        );

        history.setReason(
                result.getReason()
        );

        if (result.getEnvironmentalImpact() != null) {

            history.seteWasteDiverted(
                    result
                            .getEnvironmentalImpact()
                            .getEstimatedEwasteDivertedKg()
            );

            history.setUsefulLifeExtension(
                    result
                            .getEnvironmentalImpact()
                            .getEstimatedUsefulLifeExtensionYears()
            );
        }

        assessmentHistoryService.saveHistory(
                history
        );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAssessmentHistory(
            @PathVariable Long userId) {

        User user =
                userService
                        .getUserById(userId)
                        .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        List<Device> devices =
                deviceService.getDevicesByUser(user);

        return ResponseEntity.ok(devices);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Device>> getAllAssessments() {

        return ResponseEntity.ok(
                deviceService.getAllDevices()
        );
    }

    @GetMapping("/history/all")
    public ResponseEntity<List<AssessmentHistory>>
    getAllAssessmentHistory() {

        return ResponseEntity.ok(
                assessmentHistoryService.getAllHistory()
        );
    }

    @GetMapping("/history/user/{userId}")
    public ResponseEntity<?> getHistoryByUser(
            @PathVariable Long userId) {

        User user =
                userService
                        .getUserById(userId)
                        .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        return ResponseEntity.ok(
                assessmentHistoryService
                        .getHistoryByUser(user)
        );
    }
}