package com.ms_spring_brgy.healthAndSanitation.controller;

import com.ms_spring_brgy.constants.MappingPath;
import com.ms_spring_brgy.healthAndSanitation.model.HAS_Model;
import com.ms_spring_brgy.healthAndSanitation.services.HAS_Service;
import com.ms_spring_brgy.utils.Rest_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/has")
@RequiredArgsConstructor
public class HAS_Controller {
    private final HAS_Service service;

    //find all health and sanitation
    @GetMapping
    public ResponseEntity<List<HAS_Model>> listAllHaS() {
        return Rest_Utils.RestFindAll(service::getAllHaS);
    }

    //find by id controller
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<HAS_Model> findById(@PathVariable int id) {
        return Rest_Utils.RestFindById(() -> service.getFindById(id));
    }

    //add controller
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<HAS_Model> addHaS(@RequestBody HAS_Model body) {
        return Rest_Utils.RestAdd(() -> service.postAddHaS(body));
    }

    //update controller
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<HAS_Model> updateHaS(@RequestBody HAS_Model body) {
        return Rest_Utils.RestUpdate(() -> service.patchUpdate(body));
    }

    //delete by id controller
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteComplaint(@PathVariable int id) {
        return Rest_Utils.RestDelete(() -> service.deleteService(id));
    }
}
