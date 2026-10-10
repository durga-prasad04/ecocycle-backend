package com.ecocycle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecocycle.entity.Center;

public interface CenterRepository extends JpaRepository<Center, Long> {

    List<Center> findByType(String type);

    List<Center> findByCity(String city);

    List<Center> findByTypeAndCity(String type, String city);
}