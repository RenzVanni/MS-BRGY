package com.ms_spring_brgy.payment.dto;

import lombok.Builder;

@Builder
public record Product_Response_DTO(
        String id,
        String name,
        String description,
        Long price
) {
}
