package com.ms_spring_brgy.user.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

@Builder
public record Auth_Response_DTO(
        String id,
        String username,

        @Nullable
        String email,

        Long resident_id,
        List<String> role
) {
}
