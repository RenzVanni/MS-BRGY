package com.ms_spring_brgy.PDF_Builder.clients;

import com.ms_spring_brgy.PDF_Builder.model.Resident_Model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "residents", url = "http://localhost:7003/api/v1/residents")
public interface Residents_Client {
    @GetMapping
    List<Resident_Model> getAllResident();

    @GetMapping("/{id}")
    Resident_Model findResidentById(@PathVariable Long id);
}
