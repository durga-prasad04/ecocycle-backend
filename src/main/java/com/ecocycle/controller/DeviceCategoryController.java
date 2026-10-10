package com.ecocycle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.entity.DeviceCategory;
import com.ecocycle.service.DeviceCategoryService;

@RestController
@RequestMapping("/api/categories")
public class DeviceCategoryController {

    private final DeviceCategoryService deviceCategoryService;

    public DeviceCategoryController(DeviceCategoryService deviceCategoryService) {
        this.deviceCategoryService = deviceCategoryService;
    }

    // Create a new device category
    @PostMapping
    public DeviceCategory createCategory(@RequestBody DeviceCategory category) {
        return deviceCategoryService.saveCategory(category);
    }

    // Get all device categories
    @GetMapping
    public List<DeviceCategory> getAllCategories() {
        return deviceCategoryService.getAllCategories();
    }

    // Get category by ID
    @GetMapping("/{id}")
    public Optional<DeviceCategory> getCategoryById(@PathVariable Long id) {
        return deviceCategoryService.getCategoryById(id);
    }

    // Get category by name
    @GetMapping("/name/{name}")
    public Optional<DeviceCategory> getCategoryByName(@PathVariable String name) {
        return deviceCategoryService.getCategoryByName(name);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {

        deviceCategoryService.deleteCategory(id);

        return "Device category deleted successfully";
    }
}