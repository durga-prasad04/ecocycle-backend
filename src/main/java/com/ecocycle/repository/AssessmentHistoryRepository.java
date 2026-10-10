package com.ecocycle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecocycle.entity.AssessmentHistory;
import com.ecocycle.entity.User;

public interface AssessmentHistoryRepository
        extends JpaRepository<AssessmentHistory, Long> {

    List<AssessmentHistory> findByUser(User user);
}