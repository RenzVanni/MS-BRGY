package com.ms_spring_brgy.blotter.controller;

import com.ms_spring_brgy.blotter.model.Blotter_Model;
import com.ms_spring_brgy.blotter.services.Blotter_Service;
import com.ms_spring_brgy.constants.MappingPath;
import com.ms_spring_brgy.utils.Rest_Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blotter")
@RequiredArgsConstructor
public class Blotter_Controller {
    private final Blotter_Service service;

    //find all blotter
    @GetMapping
    public ResponseEntity<List<Blotter_Model>> allBlotter() {
        return Rest_Utils.RestFindAll(service::getAllBlotter);
    }

    //find Blotter by ID
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<Blotter_Model> findBlotterById(@PathVariable int id) {
        return Rest_Utils.RestFindById(() -> service.getBlotterById(id));
    }

    //add blotter
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<Blotter_Model> addBlotter(@RequestBody Blotter_Model body) {
        return Rest_Utils.RestAdd(() -> service.postAddBlotter(body));
    }

    //update blotter
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<Blotter_Model> updateBlotter(@RequestBody Blotter_Model body) {
        return Rest_Utils.RestUpdate(() -> service.patchUpdateBlotter(body));
    }

    //delete Blotter by Id
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteBlotterById(@PathVariable int id) {
        return Rest_Utils.RestDelete(() -> service.deleteBlotterById(id));
    }
}
