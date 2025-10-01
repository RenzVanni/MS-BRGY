package com.ms_spring_brgy.officials.repository;

import com.ms_spring_brgy.officials.model.Official_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Officials_Repository extends JpaRepository<Official_Model, Integer> {

    @Query(value = "SELECT * FROM officials LIMIT 3 OFFSET :page * 3", nativeQuery = true)
    List<Official_Model> paginateOfficials(int page);

    @Query(value = "SELECT EXISTS(SELECT * FROM officials AS p WHERE p.position = :position)", nativeQuery = true)
    Boolean isOfficialExists(String position);
}
