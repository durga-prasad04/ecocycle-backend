package com.ecocycle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.entity.Center;
import com.ecocycle.service.CenterService;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    // Add a new center
    @PostMapping
    public ResponseEntity<Center> createCenter(
            @RequestBody Center center) {

        return ResponseEntity.ok(
                centerService.saveCenter(center)
        );
    }

    // Get all centers
    @GetMapping
    public ResponseEntity<List<Center>> getAllCenters() {

        return ResponseEntity.ok(
                centerService.getAllCenters()
        );
    }

    // Get center by ID
    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(
            @PathVariable Long id) {

        return centerService.getCenterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    // Get centers by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Center>> getCentersByType(
            @PathVariable String type) {

        return ResponseEntity.ok(
                centerService.getCentersByType(type)
        );
    }

    // Get centers by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Center>> getCentersByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                centerService.getCentersByCity(city)
        );
    }

    // Get centers by type and city
    @GetMapping("/type/{type}/city/{city}")
    public ResponseEntity<List<Center>>
    getCentersByTypeAndCity(
            @PathVariable String type,
            @PathVariable String city) {

        return ResponseEntity.ok(
                centerService.getCentersByTypeAndCity(
                        type,
                        city
                )
        );
    }

    // Delete center
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(
            @PathVariable Long id) {

        centerService.deleteCenter(id);

        return ResponseEntity.noContent().build();
    }
}