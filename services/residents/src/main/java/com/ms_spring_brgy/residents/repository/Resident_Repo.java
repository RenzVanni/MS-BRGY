package com.ms_spring_brgy.residents.repository;

import com.ms_spring_brgy.residents.model.Resident_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Resident_Repo extends JpaRepository<Resident_Model, Integer> {

    @Query(value = "SELECT * FROM residents LIMIT 3 OFFSET :page * 3", nativeQuery = true)
    List<Resident_Model> paginateResident(int page);
}
