package com.ms_spring_brgy.user.dto;

import lombok.Builder;

@Builder
public record Auth_Response_DTO(
        String id,
        String username,
        String email,
        String resident_id
) {
}
