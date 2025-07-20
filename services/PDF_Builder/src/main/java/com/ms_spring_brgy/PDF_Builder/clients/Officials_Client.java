package com.ms_spring_brgy.PDF_Builder.clients;

import com.ms_spring_brgy.PDF_Builder.model.Official_Model;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "officials", url = "http://localhost:7002/api/v1/officials")
public interface Officials_Client {
    @GetMapping
    List<Official_Model> getAllOfficials();
}
