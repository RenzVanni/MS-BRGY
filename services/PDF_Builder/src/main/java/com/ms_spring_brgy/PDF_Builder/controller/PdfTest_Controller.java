package com.ms_spring_brgy.PDF_Builder.controller;

import com.ms_spring_brgy.PDF_Builder.services.PdfTest_Service;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/pdfBuilder")
@RequiredArgsConstructor
public class PdfTest_Controller {
    private final PdfTest_Service service;

    @GetMapping
    public void generatePdf(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=PdfType1.pdf");

        try {
            service.generatePdf(response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
