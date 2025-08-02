package com.ms_spring_brgy.PDF_Builder.services;

import com.ms_spring_brgy.PDF_Builder.clients.Officials_Client;
import com.ms_spring_brgy.PDF_Builder.clients.Residents_Client;
import com.ms_spring_brgy.PDF_Builder.dto.Official_DTO;
import com.ms_spring_brgy.PDF_Builder.model.Official_Model;
import com.ms_spring_brgy.PDF_Builder.model.Resident_Model;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PdfTest_Service {
    private final TemplateEngine templateEngine;
    private final Officials_Client officialsClient;
    private final Residents_Client residentsClient;

    //create variables this will contain the data that will be use in html
    Map<String, Object> variables = new HashMap<>();

    LocalDate today = LocalDate.now();
    int currentDay = today.getDayOfMonth();
    String currentMonth = today.format(DateTimeFormatter.ofPattern("MMMM"));
    int currentYear = today.getYear();

    LocalDate renewDate = today.plusYears(1);
    String renewMonth = renewDate.format(DateTimeFormatter.ofPattern("MMMM"));
    int renewYear = renewDate.getYear();

    //img
    String imgPath = "https://res.cloudinary.com/dwwcralpu/image/upload/v1752694314/ian_002_kn0cke.jpg";
    String imgPath2 = "https://res.cloudinary.com/dwwcralpu/image/upload/v1752694336/wonyoung_icons_xle48f.jpg";
    String imgPath3 = "https://res.cloudinary.com/dwwcralpu/image/upload/v1742754952/residents/hbfdrnnhzndmgdpnonvl.jpg";


    //get officials
    private List<Official_DTO> getAllOfficials() {
        return officialsClient.getAllOfficials()
                .stream()
                .map(official -> {
                    Resident_Model resident = residentsClient.findResidentById(official.getResident_id());
                    String validateResidentMiddleName = Objects.nonNull(resident.getMiddlename()) ? " " + resident.getMiddlename() + " " : " ";
                    String residentName = resident.getFirstname() + validateResidentMiddleName + resident.getLastname();
                    return new Official_DTO(residentName, official.getPosition());
                })
                .toList();
    }

    //El kapitan
    private Official_DTO getKapitan() {
        Official_Model kapitan = officialsClient.getAllOfficials()
                .stream()
                .filter(official -> official.getPosition().equals("PUNONG_BARANGAY"))
                .findFirst()
                .orElse(new Official_Model());

        Resident_Model resident = residentsClient.findResidentById(kapitan.getResident_id());
        String validateResidentMiddleName = Objects.nonNull(resident.getMiddlename()) ? " " + resident.getMiddlename() + " " : " ";
        String residentName = resident.getFirstname() + validateResidentMiddleName + resident.getLastname();
        return new Official_DTO(residentName, kapitan.getPosition());

    }

    //default data
    private void defaultData() {
        //brgy info
        variables.put("title", "barangay zone iv");
        variables.put("province", "cavite");
        variables.put("city", "dasmarinas");
        variables.put("telNo", "(046) 471-1247");
        variables.put("officialName", "Jia Shie");
        variables.put("position", "punong barangay");
        variables.put("officials", getAllOfficials());
        variables.put("kapitan", getKapitan());

        //color
        variables.put("color", "#cdb4db");

        //date
        variables.put("day", currentDay);
        variables.put("month", currentMonth);
        variables.put("year", currentYear);

        //img path
        variables.put("imgPath", imgPath);
    }

    //generate pdf
    public void generatePdf(OutputStream outputStream) throws IOException {
        Context context = new Context();

        System.out.println("This are residents: " + residentsClient.getAllResident());

        defaultData();

        //html path
        variables.put("fragmentPath", "fragments/Brgy_Clearance");

        //pdf content
        variables.put("certificate", "barangay clearance");


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

    //generate certificate of indigency
    public void indigencyCert(OutputStream outputStream) throws IOException {
        Context context = new Context();

        defaultData();

        //html path
        variables.put("fragmentPath", "fragments/Indigency");


        //pdf content
        variables.put("certificate", "certificate of indigency");
        variables.put("toName", "yul");
        variables.put("toPosition", "mid lane");
        variables.put("zone", "iv");
        variables.put("applicantName", "zed");
        variables.put("requestorName", "janna");
        variables.put("address", "shadow street 00x");
        variables.put("purpose", "this is the NuN");


        //pdf type
        variables.put("forSelf", false);

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

    //generate certificate of business clearance
    public void businessClearance(OutputStream outputStream) throws IOException {
        Context context = new Context();

        defaultData();

        //html path
        variables.put("fragmentPath", "fragments/Business_Clearance");

        //pdf content
        variables.put("certificate", "barangay business clearance");

        //renew date
        variables.put("renewMonth", renewMonth);
        variables.put("renewYear", renewYear);

        //pdf type
        variables.put("forSelf", true);

        //business info
        variables.put("businessName", "RingEx");
        variables.put("businessAddress", "Fallen Street 00X");
        variables.put("businessOwner", "zzang");

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

    //generate endorsement
    public void endorsement(OutputStream outputStream) throws IOException {
        Context context = new Context();

        defaultData();

        //html path
        variables.put("fragmentPath", "fragments/Endorsement");

        //pdf content
        variables.put("certificate", "endorsement");
        variables.put("toName", "yul");
        variables.put("toPosition", "mid lane");
        variables.put("zone", "iv");
        variables.put("applicantName", "zed");
        variables.put("requestorName", "janna");
        variables.put("address", "shadow street 00x");
        variables.put("purpose", "this is the NuN");

        //pdf type
        variables.put("forSelf", true);

        ClassPathResource gradient = new ClassPathResource("/static/pdfGradient.png");
        byte[] gradientBytes = FileCopyUtils.copyToByteArray(gradient.getInputStream());
        String gradientBase = Base64.getEncoder().encodeToString(gradientBytes);
        variables.put("gradient", gradientBase);


        context.setVariables(variables);
        String htmlContent = templateEngine.process("PdfType2", context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    //generate late birth registration
    public void lateBirthRegistration(OutputStream outputStream) throws IOException {
        Context context = new Context();

        defaultData();

        //html path
        variables.put("fragmentPath", "fragments/Late_Birth_Registration");

        //pdf content
        variables.put("certificate", "certificate of late birth registration");
        variables.put("toName", "yul");
        variables.put("toPosition", "mid lane");
        variables.put("zone", "iv");
        variables.put("applicantName", "zed");
        variables.put("requestorName", "janna");
        variables.put("address", "shadow street 00x");
        variables.put("purpose", "this is the NuN");

        //pdf type
        variables.put("forSelf", true);

        ClassPathResource gradient = new ClassPathResource("/static/pdfGradient.png");
        byte[] gradientBytes = FileCopyUtils.copyToByteArray(gradient.getInputStream());
        String gradientBase = Base64.getEncoder().encodeToString(gradientBytes);
        variables.put("gradient", gradientBase);


        context.setVariables(variables);
        String htmlContent = templateEngine.process("PdfType2", context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);
    }
}
