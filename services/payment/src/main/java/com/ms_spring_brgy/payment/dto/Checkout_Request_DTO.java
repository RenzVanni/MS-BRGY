package com.ms_spring_brgy.payment.dto;

public record Checkout_Request_DTO(
        String productId,
        Long quantity
) {
}
