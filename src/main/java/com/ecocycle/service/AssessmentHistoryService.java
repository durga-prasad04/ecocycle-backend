package com.ecocycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecocycle.entity.AssessmentHistory;
import com.ecocycle.entity.User;
import com.ecocycle.repository.AssessmentHistoryRepository;

@Service
public class AssessmentHistoryService {

    private final AssessmentHistoryRepository assessmentHistoryRepository;

    public AssessmentHistoryService(
            AssessmentHistoryRepository assessmentHistoryRepository) {

        this.assessmentHistoryRepository =
                assessmentHistoryRepository;
    }


    // Save assessment history
    public AssessmentHistory saveHistory(
            AssessmentHistory history) {

        return assessmentHistoryRepository.save(history);
    }


    // Get all assessment history
    public List<AssessmentHistory> getAllHistory() {

        return assessmentHistoryRepository.findAll();
    }


    // Get assessment history by ID
    public Optional<AssessmentHistory> getHistoryById(
            Long id) {

        return assessmentHistoryRepository.findById(id);
    }


    // Get assessment history for a user
    public List<AssessmentHistory> getHistoryByUser(
            User user) {

        return assessmentHistoryRepository
                .findByUser(user);
    }


    // Delete assessment history
    public void deleteHistory(Long id) {

        assessmentHistoryRepository.deleteById(id);
    }
}