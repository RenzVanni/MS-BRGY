package com.ms_spring_brgy.residents.repository;

import com.ms_spring_brgy.residents.model.Resident_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Resident_Repo extends JpaRepository<Resident_Model, Integer> {
}
