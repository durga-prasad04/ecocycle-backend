package com.ecocycle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecocycle.entity.Device;
import com.ecocycle.entity.User;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByUser(User user);

    List<Device> findByBrand(String brand);
}