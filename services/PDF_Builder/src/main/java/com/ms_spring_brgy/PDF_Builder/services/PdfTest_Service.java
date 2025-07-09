package com.ms_spring_brgy.PDF_Builder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfTest_Service {
    private final TemplateEngine templateEngine;

    //generate pdf
    public void generatePdf(OutputStream outputStream) throws IOException {
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "barangay zone iv");
        variables.put("certificate", "barangay clearance");
        variables.put("fragmentPath", "fragments/Brgy_Clearance");
        variables.put("province", "cavite");
        variables.put("city", "dasmarinas");
        variables.put("telNo", "(046) 471-1247");
        variables.put("name", "Jia Shie");
        variables.put("position", "punong barangay");
        variables.put("officials", List.of(
                Map.of("name", "wonyoung", "position", "secretary"),
                Map.of("name", "karina", "position", "treasurer")
        ));

        byte[] imageBytes = Files.readAllBytes(Paths.get("C:\\Users\\Admin\\OneDrive\\Pictures\\zzang\\ian_(1).jpeg"));
        String mimeType = Files.probeContentType(Paths.get("C:\\Users\\Admin\\OneDrive\\Pictures\\zzang\\ian_(1).jpeg"));
        String base = Base64.getEncoder().encodeToString(imageBytes);
        variables.put("image", base);
        variables.put("imageType", mimeType);

        ClassPathResource gradient = new ClassPathResource("/static/pdfGradient.png");
        byte[] gradientBytes = FileCopyUtils.copyToByteArray(gradient.getInputStream());
        String gradientBase = Base64.getEncoder().encodeToString(gradientBytes);
        variables.put("gradient", gradientBase);

        context.setVariables(variables);
        String htmlContent = templateEngine.process("PdfType1", context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
