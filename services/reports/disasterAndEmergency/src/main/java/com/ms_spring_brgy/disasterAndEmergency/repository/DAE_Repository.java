package com.ms_spring_brgy.disasterAndEmergency.repository;

import com.ms_spring_brgy.disasterAndEmergency.model.DAE_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAE_Repository extends JpaRepository<DAE_Model, Integer> {
}
