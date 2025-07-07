package com.ms_spring_brgy.PDF_Builder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfTest_Service {
    private final TemplateEngine templateEngine;

    //generate pdf
    public void generatePdf(OutputStream outputStream) {
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "barangay zone iv");
        variables.put("province", "cavite");
        variables.put("city", "dasmarinas");
        variables.put("telNo", "(046) 471-1247");
        context.setVariables(variables);
        String htmlContent = templateEngine.process("testPdf", context);

        System.out.println("Html content: " + htmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
