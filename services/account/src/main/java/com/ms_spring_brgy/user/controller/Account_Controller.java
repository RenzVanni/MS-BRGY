package com.ms_spring_brgy.user.controller;

import com.ms_spring_brgy.user.constants.UserData;
import com.ms_spring_brgy.user.controller.component.Rest_Component;
import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import com.ms_spring_brgy.user.dto.ImportedUserDTO;
import com.ms_spring_brgy.user.dto.LoginRequestDTO;
import com.ms_spring_brgy.user.dto.UpdateUserRequestDTO;
import com.ms_spring_brgy.user.model.Account_Model;
import com.ms_spring_brgy.user.services.Account_Service;
import com.ms_spring_brgy.user.services.Keycloak_Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.WebApplicationException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class Account_Controller {
//    private final Account_Service service;
    private final Keycloak_Service keycloakService;

    /**
     * Register
     * @param body
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<UserRepresentation> createUserInKeycloak(@Valid @RequestBody UserData body) {
//        try {
            keycloakService.createUser(body);
            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
    }


    /**
     * Login
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
        String token = keycloakService.accessToken(request);

        ResponseCookie cookie = ResponseCookie.from("access_token", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(60 * 60)
                .build();

//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login Successfully");

    }

    /**
     * Fetch token from keycloak
     * @param request
     * @return
     */
    @GetMapping("/token")
    public ResponseEntity<String> fetchToken(HttpServletRequest request) {
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("access_token")) {
                    return ResponseEntity.ok(cookie.getValue());
                }
            }
        }
        return null;
    }

    /**
     * Logout
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("access_token")
                .path("/")
                .maxAge(0)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .build();
    }

    /**
     * Count users
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> countUsers() {
        Integer body = keycloakService.countUsers();
        return ResponseEntity.ok(body);
    }

    /**
     * Export all accounts
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void exportAccounts(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");

        List<Auth_Response_DTO> data = keycloakService.getUsers();

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Username");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Resident ID");
        headerRow.createCell(4).setCellValue("Role");

        // Populate data row
        int rowNum = 1;
        for(Auth_Response_DTO account : data) {
            Row dataRow = sheet.createRow(rowNum++);
            if(account.resident_id() != null) {
                dataRow.createCell(0).setCellValue(account.id());
                dataRow.createCell(1).setCellValue(account.username());
                dataRow.createCell(2).setCellValue(account.email());
                dataRow.createCell(3).setCellValue(account.resident_id());
                dataRow.createCell(4).setCellValue(account.role().toString());
            }
        }

        workbook.write(response.getOutputStream());
        workbook.close();

    }

    // Import Accounts
    @PostMapping("/uploadExcel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") File file) {
//        if(file) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Please select a file to upload.");
//        }

        List<Auth_Response_DTO> entities = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if(row.getRowNum() == 0) {
                    continue;
                }

                Long residentId = Long.valueOf(row.getCell(3).getStringCellValue());

                UserData entity = UserData.builder()
                        .username(row.getCell(1).getStringCellValue())
                        .email(row.getCell(2).getStringCellValue())
                        .password("12345678")
                        .confirmPassword("12345678")
                        .residentId(residentId)
                        .role(row.getCell(4).getStringCellValue())
                        .build();



            }

            return ResponseEntity.ok().body("Imported");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find all users
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Auth_Response_DTO>> findAllAccount() {
        return Rest_Component.RestFindAll(keycloakService::getUsers);
    }

    /**
     * Find user by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Auth_Response_DTO> findUserById(@PathVariable String id) {
        return Rest_Component.RestFindById(() -> keycloakService.fetchUserById(id));
    }

    /**
     * Update user
     * @param body
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody UpdateUserRequestDTO body) {
        try {
            keycloakService.updateUser(body);

            return ResponseEntity
                    .ok()
                    .body("User Update Successfully!");

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Delete by ID
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable String id) {
        keycloakService.deleteUser(id);

        return ResponseEntity
                .ok()
                .body("User Deleted Successfully");
    }
}
