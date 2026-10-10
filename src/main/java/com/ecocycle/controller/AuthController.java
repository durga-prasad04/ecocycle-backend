package com.ecocycle.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecocycle.dto.LoginRequest;
import com.ecocycle.dto.LoginResponse;
import com.ecocycle.entity.User;
import com.ecocycle.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        // Validate request
        if (request.getEmail() == null
                || request.getEmail().isBlank()
                || request.getPassword() == null
                || request.getPassword().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("Email and password are required");
        }

        // Authenticate user
        Optional<User> userOptional =
                userService.authenticateUser(
                        request.getEmail(),
                        request.getPassword()
                );

        // Invalid credentials
        if (userOptional.isEmpty()) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }

        // Successful login
        User user = userOptional.get();

        LoginResponse response =
                new LoginResponse(
                        "Login successful",
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                );

        return ResponseEntity.ok(response);
    }
}