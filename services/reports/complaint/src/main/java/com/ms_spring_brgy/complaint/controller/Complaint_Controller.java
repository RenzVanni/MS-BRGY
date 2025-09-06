package com.ms_spring_brgy.complaint.controller;

import com.ms_spring_brgy.complaint.constants.MappingPath;
import com.ms_spring_brgy.complaint.model.Complaint_Model;
import com.ms_spring_brgy.complaint.services.Complaint_Service;
import com.ms_spring_brgy.complaint.utils.Rest_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaint")
@RequiredArgsConstructor
public class Complaint_Controller {
    private final Complaint_Service service;

    //find all complaint
    @GetMapping
    public ResponseEntity<List<Complaint_Model>> listAllComplaint() {
        return Rest_Utils.RestFindAll(service::getAllComplain);
    }

    //find by id controller
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<Complaint_Model> findById(@PathVariable int id) {
        return Rest_Utils.RestFindById(() -> service.getFindById(id));
    }

    //add controller
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<Complaint_Model> addComplaint(@RequestBody Complaint_Model body) {
        return Rest_Utils.RestAdd(() -> service.postAddComplaint(body));
    }

    //update controller
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<Complaint_Model> updateComplaint(@RequestBody Complaint_Model body) {
        return Rest_Utils.RestUpdate(() -> service.patchUpdate(body));
    }

    //delete by id controller
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteComplaint(@PathVariable int id) {
        return Rest_Utils.RestDelete(() -> service.deleteService(id));
    }
}
