package com.ms_spring_brgy.user.repository;

import com.ms_spring_brgy.user.model.Account_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_Repo extends JpaRepository<Account_Model, Integer> {
}
