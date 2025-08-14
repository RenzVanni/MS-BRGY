package com.ms_spring_brgy.notification.controller;

import com.ms_spring_brgy.notification.services.Email_Service;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/notification")
@RequiredArgsConstructor
public class Email_Controller {
    private final Email_Service service;

    @GetMapping
    public ResponseEntity<String> sendEmail() throws MessagingException, InterruptedException {
        service.sendEmail();
        System.out.println("First Message 001");

        return ResponseEntity.ok("Email Send");
    }
}
