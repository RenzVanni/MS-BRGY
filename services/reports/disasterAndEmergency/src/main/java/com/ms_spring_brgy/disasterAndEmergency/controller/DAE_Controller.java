package com.ms_spring_brgy.disasterAndEmergency.controller;

import com.ms_spring_brgy.disasterAndEmergency.constants.MappingPath;
import com.ms_spring_brgy.disasterAndEmergency.model.DAE_Model;
import com.ms_spring_brgy.disasterAndEmergency.services.DAE_Service;
import com.ms_spring_brgy.disasterAndEmergency.utils.Rest_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dae")
@RequiredArgsConstructor
public class DAE_Controller {
    private final DAE_Service service;

    //find all disaster and emergency
    @GetMapping
    public ResponseEntity<List<DAE_Model>> listAllDaE() {
        return Rest_Utils.RestFindAll(service::getAllDaE);
    }

    //find by id
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<DAE_Model> findById(@PathVariable int id) {
        return Rest_Utils.RestFindById(() -> service.getFindById(id));
    }

    //add controller
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<DAE_Model> addDaE(@RequestBody  DAE_Model body) {
        return Rest_Utils.RestAdd(() -> service.postAddDaE(body));
    }

    //update controller
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<DAE_Model> update(@RequestBody DAE_Model body) {
        return Rest_Utils.RestUpdate(() -> service.patchUpdate(body));
    }

    //delete by id controller
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteComplaint(@PathVariable int id) {
        return Rest_Utils.RestDelete(() -> service.deleteService(id));
    }
}
