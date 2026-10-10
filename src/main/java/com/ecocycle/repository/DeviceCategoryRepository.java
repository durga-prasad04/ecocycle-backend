package com.ecocycle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecocycle.entity.DeviceCategory;

public interface DeviceCategoryRepository extends JpaRepository<DeviceCategory, Long> {

    Optional<DeviceCategory> findByName(String name);
}