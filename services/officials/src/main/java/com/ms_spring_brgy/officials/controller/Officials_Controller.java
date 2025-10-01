package com.ms_spring_brgy.officials.controller;

import com.ms_spring_brgy.officials.controller.component.Rest_Component;
import com.ms_spring_brgy.officials.dto.RequestOfficialDTO;
import com.ms_spring_brgy.officials.dto.UpdateOfficialDTO;
import com.ms_spring_brgy.officials.model.Official_Model;
import com.ms_spring_brgy.officials.services.Officials_Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/officials")
@RequiredArgsConstructor
public class Officials_Controller {
    private final Officials_Service service;

    /**
     * Paginate officials
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Official_Model>> paginateOfficials(@RequestParam(name = "page", defaultValue = "0") int page) {
        return Rest_Component.RestFindAll(() -> service.paginateOfficials(page));
    }

    /**
     * Find official by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Official_Model> findOfficialById(@PathVariable int id) {
        return Rest_Component.RestFindById(() -> service.getOfficialById(id));
    }

    /**
     * Create official
     * @param body
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Official_Model> createOfficial(@Valid @RequestBody RequestOfficialDTO body) {
        return Rest_Component.RestAdd(() -> service.createOfficial(body));
    }

    //update official
    @PatchMapping("/update")
    public ResponseEntity<Official_Model> updateOfficial(@Valid @RequestBody UpdateOfficialDTO body) {
        return  Rest_Component.RestUpdate(() -> service.patchUpdateOfficial(body));
    }
    //delete by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOfficialById(@PathVariable int id) {
        return Rest_Component.RestDelete(() -> service.deleteOfficialById(id));
    }
}
