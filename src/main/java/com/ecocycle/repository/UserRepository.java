package com.ecocycle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecocycle.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}