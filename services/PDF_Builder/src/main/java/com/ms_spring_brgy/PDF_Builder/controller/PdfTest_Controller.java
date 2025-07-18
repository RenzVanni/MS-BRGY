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

    //barangay clearance
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

    //indigency
    @GetMapping("/indigency")
    public void indigencyCert(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=PdfType1.pdf");

        try {
            service.indigencyCert(response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    //business clearance
    @GetMapping("/businessClearance")
    public void businessClearance(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=PdfType1.pdf");

        try {
            service.businessClearance(response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    //endorsement
    @GetMapping("/endorsement")
    public void endorsement(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=PdfType2.pdf");

        try {
            service.endorsement(response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    //late birth registration
    @GetMapping("/lateBirthRegistration")
    public void lateBirthRegistration(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=PdfType1.pdf");

        try {
            service.lateBirthRegistration(response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
