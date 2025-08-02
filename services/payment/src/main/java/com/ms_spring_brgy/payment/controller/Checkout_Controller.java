package com.ms_spring_brgy.payment.controller;

import com.ms_spring_brgy.payment.dto.Checkout_Request_DTO;
import com.ms_spring_brgy.payment.services.Checkout_Service;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/checkout")
@RequiredArgsConstructor
public class Checkout_Controller {
    private final Checkout_Service service;

    //accept payment
    @PostMapping
    public ResponseEntity<Map<String, String>> acceptPayment(@RequestBody Checkout_Request_DTO checkout) throws StripeException {
        Map<String, String> response = service.acceptPayment(checkout);
        return ResponseEntity.ok(response);
    }
}
