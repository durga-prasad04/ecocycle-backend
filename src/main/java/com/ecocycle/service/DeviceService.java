package com.ecocycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.Device;
import com.ecocycle.entity.User;
import com.ecocycle.repository.DeviceRepository;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getDevicesByUser(User user) {
        return deviceRepository.findByUser(user);
    }

    public List<Device> getDevicesByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}