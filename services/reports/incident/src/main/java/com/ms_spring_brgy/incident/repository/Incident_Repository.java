package com.ms_spring_brgy.incident.repository;

import com.ms_spring_brgy.incident.model.Incident_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Incident_Repository extends JpaRepository<Incident_Model, Integer> {
}
