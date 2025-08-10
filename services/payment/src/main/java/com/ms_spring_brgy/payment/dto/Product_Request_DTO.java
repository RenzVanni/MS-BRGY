package com.ms_spring_brgy.payment.dto;

public record Product_Request_DTO(
        String name,
        String description,
        int price
) {
}
