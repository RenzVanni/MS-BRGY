package com.ms_spring_brgy.officials.repository;

import com.ms_spring_brgy.officials.model.Official_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Officials_Repository extends JpaRepository<Official_Model, Integer> {
}
