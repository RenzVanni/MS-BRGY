package com.ms_spring_brgy.user.controller;

import com.ms_spring_brgy.user.constants.UserData;
import com.ms_spring_brgy.user.controller.component.Rest_Component;
import com.ms_spring_brgy.user.dto.Auth_Response_DTO;
import com.ms_spring_brgy.user.dto.LoginRequestDTO;
import com.ms_spring_brgy.user.model.Account_Model;
import com.ms_spring_brgy.user.services.Account_Service;
import com.ms_spring_brgy.user.services.Keycloak_Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class Account_Controller {
    private final Account_Service service;
    private final Keycloak_Service keycloakService;

    //register user in keycloak
    @PostMapping("/register")
    public ResponseEntity<UserRepresentation> createUserInKeycloak(@RequestBody UserData body) {
        keycloakService.createUser(body);
        return ResponseEntity.ok().build();
    }


    //login
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

    //fetch token
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

    //logout
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

    //fetch all users in keycloak
    @GetMapping("/users")
    public ResponseEntity<List<Auth_Response_DTO>> getKeycloakUser() {
        List<Auth_Response_DTO> body = keycloakService.getUsers();
        return ResponseEntity.ok(body);
    }

    //find all accounts
    @GetMapping
    public ResponseEntity<List<Auth_Response_DTO>> findAllAccount() {
        return Rest_Component.RestFindAll(keycloakService::getUsers);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Account_Model> findUserById(@PathVariable int id) {
        return Rest_Component.RestFindById(() -> service.getUserById(id));
    }

    //update account
    @PatchMapping("/update")
    public ResponseEntity<Account_Model> updateAccount(@RequestBody Account_Model body) {
        try {
            Account_Model response = service.patchUpdateAccount(body);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    //delete by Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable int id) {
        return Rest_Component.RestDelete(() -> service.deleteAccountById(id));
    }
}
