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

import com.ecocycle.entity.Device;
import com.ecocycle.entity.User;
import com.ecocycle.service.DeviceService;
import com.ecocycle.service.UserService;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final UserService userService;

    public DeviceController(DeviceService deviceService, UserService userService) {
        this.deviceService = deviceService;
        this.userService = userService;
    }

    // Create a new device
    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    // Get all devices
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Get device by ID
    @GetMapping("/{id}")
    public Optional<Device> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    // Get all devices belonging to a user
    @GetMapping("/user/{userId}")
    public List<Device> getDevicesByUser(@PathVariable Long userId) {

        Optional<User> user = userService.getUserById(userId);

        if (user.isPresent()) {
            return deviceService.getDevicesByUser(user.get());
        }

        return List.of();
    }

    // Get devices by brand
    @GetMapping("/brand/{brand}")
    public List<Device> getDevicesByBrand(@PathVariable String brand) {
        return deviceService.getDevicesByBrand(brand);
    }

    // Delete device
    @DeleteMapping("/{id}")
    public String deleteDevice(@PathVariable Long id) {

        deviceService.deleteDevice(id);

        return "Device deleted successfully";
    }
}