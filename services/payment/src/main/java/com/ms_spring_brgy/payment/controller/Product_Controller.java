package com.ms_spring_brgy.payment.controller;

import com.ms_spring_brgy.payment.dto.Checkout_Request_DTO;
import com.ms_spring_brgy.payment.dto.Product_Request_DTO;
import com.ms_spring_brgy.payment.dto.Product_Response_DTO;
import com.ms_spring_brgy.payment.services.Checkout_Service;
import com.ms_spring_brgy.payment.services.Product_Service;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/product")
@RequiredArgsConstructor
public class Product_Controller {
    private final Product_Service service;

    @GetMapping("/success")
    public String successRedirect() {
        return "Payment Successfully received!";
    }

    @GetMapping("/cancel")
    public String cancelRedirect() {
        return "Payment Canceled!";
    }

    //create product
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product_Request_DTO body) throws StripeException {
        String response = service.createProduct(body);
        return ResponseEntity.ok(response);
    }

    //fetch products
    @GetMapping
    public ResponseEntity<List<Product_Response_DTO>> fetchProducts() throws StripeException {
        List<Product_Response_DTO> response = service.fetchProducts();
        return ResponseEntity.ok(response);
    }
}
