package com.ms_spring_brgy.residents.controller;

import com.ms_spring_brgy.residents.constants.MappingPath;
import com.ms_spring_brgy.residents.controller.component.REST_Component;
import com.ms_spring_brgy.residents.enums.Citizenship;
import com.ms_spring_brgy.residents.model.Resident_Model;
import com.ms_spring_brgy.residents.services.Resident_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/residents")
@RequiredArgsConstructor
public class Residents_Controller {
    private final Resident_Service service;

    @GetMapping("/enum")
    public ResponseEntity<List<Map<String, String>>> enumList() {
        List<Map<String, String>> list = Arrays.stream(Citizenship.values())
                .map(val -> Map.of("value", val.name(), "label", val.getLabel()))
                .toList();
        return ResponseEntity.ok(list);
    }

    /**
     * Paginate residents
     * @param page
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Resident_Model>> paginateResidents(@RequestParam(defaultValue = "0") int page) {
        return REST_Component.RestFindAll(() -> service.paginateResidents(page));
    }

    //find resident by ID
    @GetMapping(MappingPath.FIND_BY_ID)
    public ResponseEntity<Resident_Model> findResidentById(@PathVariable int id) {
        return REST_Component.RestFindById(() -> service.getResidentById(id));
    }

    //add resident
    @PostMapping(MappingPath.ADD)
    public ResponseEntity<Resident_Model> addResident(@RequestPart MultipartFile imageFile,
                                                     @RequestPart Resident_Model resident) {
        return REST_Component.RestAdd(() -> service.postAddResident(imageFile, resident));
    }

    //multiple upload
    @PostMapping("/multiple")
    public ResponseEntity<List<Resident_Model>> multipleUpload(@RequestPart List<MultipartFile> imageFile,
                                                              @RequestPart List<Resident_Model> body) {
        return REST_Component.RestAddMultipart(() -> service.postMultipleUpload(imageFile, body));
    }

    //update resident
    @PatchMapping(MappingPath.UPDATE)
    public ResponseEntity<Resident_Model> updateResident(@RequestPart(required = false) MultipartFile file,
                                                        @RequestPart Resident_Model resident) {
        return REST_Component.RestUpdate(() -> service.patchUpdateResident(file, resident));
    }

    //delete by Id
    @DeleteMapping(MappingPath.DELETE_BY_ID)
    public ResponseEntity<String> deleteResidentById(@PathVariable int id) {
        return REST_Component.RestDelete(() -> service.deleteResidentById(id));
    }
}
