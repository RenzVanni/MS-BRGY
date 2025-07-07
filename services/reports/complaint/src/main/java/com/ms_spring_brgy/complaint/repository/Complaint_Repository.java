package com.ms_spring_brgy.complaint.repository;

import com.ms_spring_brgy.complaint.model.Complaint_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Complaint_Repository extends JpaRepository<Complaint_Model, Integer> {
}
