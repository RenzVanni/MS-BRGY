package com.ms_spring_brgy.healthAndSanitation.repository;

import com.ms_spring_brgy.healthAndSanitation.model.HAS_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HAS_Repository extends JpaRepository<HAS_Model, Integer> {
}
