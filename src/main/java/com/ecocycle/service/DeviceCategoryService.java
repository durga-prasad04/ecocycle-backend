package com.ecocycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.DeviceCategory;
import com.ecocycle.repository.DeviceCategoryRepository;

@Service
public class DeviceCategoryService {

    private final DeviceCategoryRepository deviceCategoryRepository;

    public DeviceCategoryService(DeviceCategoryRepository deviceCategoryRepository) {
        this.deviceCategoryRepository = deviceCategoryRepository;
    }

    public DeviceCategory saveCategory(DeviceCategory category) {
        return deviceCategoryRepository.save(category);
    }

    public List<DeviceCategory> getAllCategories() {
        return deviceCategoryRepository.findAll();
    }

    public Optional<DeviceCategory> getCategoryById(Long id) {
        return deviceCategoryRepository.findById(id);
    }

    public Optional<DeviceCategory> getCategoryByName(String name) {
        return deviceCategoryRepository.findByName(name);
    }

    public void deleteCategory(Long id) {
        deviceCategoryRepository.deleteById(id);
    }
}