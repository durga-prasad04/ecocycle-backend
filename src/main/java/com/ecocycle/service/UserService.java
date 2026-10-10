package com.ecocycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecocycle.entity.User;
import com.ecocycle.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {

        if (user.getPassword() != null
                && !user.getPassword().isBlank()
                && !isPasswordAlreadyEncoded(user.getPassword())) {

            user.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(
            String email,
            String password) {

        System.out.println("LOGIN EMAIL: " + email);
        System.out.println("PASSWORD RECEIVED: "
                + (password != null && !password.isBlank()));

        Optional<User> userOptional =
                userRepository.findByEmail(email);

        System.out.println("USER FOUND: " + userOptional.isPresent());

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            boolean matches =
                    passwordEncoder.matches(
                            password,
                            user.getPassword()
                    );

            System.out.println("PASSWORD MATCH: " + matches);

            if (matches) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private boolean isPasswordAlreadyEncoded(String password) {

        return password.startsWith("$2a$")
                || password.startsWith("$2b$")
                || password.startsWith("$2y$");
    }
}