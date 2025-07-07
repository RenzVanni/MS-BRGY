package com.ms_spring_brgy.blotter.repository;

import com.ms_spring_brgy.blotter.model.Blotter_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Blotter_Repository extends JpaRepository<Blotter_Model, Integer> {
}
