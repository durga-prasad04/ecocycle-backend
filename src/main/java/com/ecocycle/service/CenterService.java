package com.ecocycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.Center;
import com.ecocycle.repository.CenterRepository;

@Service
public class CenterService {

    private final CenterRepository centerRepository;

    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public Center saveCenter(Center center) {
        return centerRepository.save(center);
    }

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Optional<Center> getCenterById(Long id) {
        return centerRepository.findById(id);
    }

    public List<Center> getCentersByType(String type) {
        return centerRepository.findByType(type);
    }

    public List<Center> getCentersByCity(String city) {
        return centerRepository.findByCity(city);
    }

    public List<Center> getCentersByTypeAndCity(
            String type,
            String city) {

        return centerRepository.findByTypeAndCity(type, city);
    }

    public void deleteCenter(Long id) {
        centerRepository.deleteById(id);
    }
}