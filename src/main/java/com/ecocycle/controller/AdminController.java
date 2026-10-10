package com.ecocycle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.entity.Device;
import com.ecocycle.entity.User;
import com.ecocycle.service.DeviceService;
import com.ecocycle.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final DeviceService deviceService;

    public AdminController(
            UserService userService,
            DeviceService deviceService) {

        this.userService = userService;
        this.deviceService = deviceService;
    }

    // =========================================
    // GET ALL USERS
    // =========================================

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }


    // =========================================
    // GET USER BY ID
    // =========================================

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }


    // =========================================
    // GET ALL DEVICES
    // =========================================

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {

        return ResponseEntity.ok(
                deviceService.getAllDevices()
        );
    }


    // =========================================
    // DELETE USER
    // =========================================

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }


    // =========================================
    // DELETE DEVICE
    // =========================================

    @DeleteMapping("/devices/{id}")
    public ResponseEntity<Void> deleteDevice(
            @PathVariable Long id) {

        deviceService.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }
}