package com.ms_spring_brgy.incident.controller;

import com.ms_spring_brgy.constants.MappingPath;
import com.ms_spring_brgy.incident.model.Incident_Model;
import com.ms_spring_brgy.incident.service.Incident_Service;
import com.ms_spring_brgy.utils.Rest_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incident")
@RequiredArgsConstructor
public class Incident_Controller {
    private final Incident_Service service;

    //find all incident
    @GetMapping
    public ResponseEntity<List<Incident_Model>> listAllIncident() {
        return Rest_Utils.RestFindAll(service::getAllIncident);
    }

    //find by id controller
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<Incident_Model> findById(@PathVariable int id) {
        return Rest_Utils.RestFindById(() -> service.getFindById(id));
    }

    //add controller
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<Incident_Model> addIncident(@RequestBody Incident_Model body) {
        return Rest_Utils.RestAdd(() -> service.postAddIncident(body));
    }

    //update controller
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<Incident_Model> updateIncident(@RequestBody Incident_Model body) {
        return Rest_Utils.RestUpdate(() -> service.patchUpdate(body));
    }

    //delete by id controller
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteComplaint(@PathVariable int id) {
        return Rest_Utils.RestDelete(() -> service.deleteService(id));
    }
}
