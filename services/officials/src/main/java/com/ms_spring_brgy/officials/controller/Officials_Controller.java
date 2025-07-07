package com.ms_spring_brgy.officials.controller;

import com.ms_spring_brgy.officials.controller.component.Rest_Component;
import com.ms_spring_brgy.officials.model.Official_Model;
import com.ms_spring_brgy.officials.services.Officials_Service;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/officials")
@RequiredArgsConstructor
public class Officials_Controller {
    private final Officials_Service service;

    //list all officials
    @GetMapping
    public ResponseEntity<List<Official_Model>> allOfficials() {
        return Rest_Component.RestFindAll(service::getAllOfficials);
    }

    //find official by id
    @GetMapping("/{id}")
    public ResponseEntity<Official_Model> findOfficialById(@PathVariable int id) {
        return Rest_Component.RestFindById(() -> service.getOfficialById(id));
    }

    //add official
    @PostMapping("/add")
    public ResponseEntity<Official_Model> addOfficial(@RequestBody Official_Model body) {
        return Rest_Component.RestAdd(() -> service.postAddOfficial(body));
    }

    //update official
    @PatchMapping("/update")
    public ResponseEntity<Official_Model> updateOfficial(@RequestBody Official_Model body) {
        return  Rest_Component.RestUpdate(() -> service.patchUpdateOfficial(body));
    }
    //delete by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOfficialById(@PathVariable int id) {
        return Rest_Component.RestDelete(() -> service.deleteOfficialById(id));
    }
}
