package com.ms_spring_brgy.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ImportedUserDTO(
        @NotNull(message = "Username required!")
        String username,

        @NotNull(message = "Resident ID required!")
        String email,

        @NotNull(message = "Resident ID required!")
        Long residentId,

        @NotNull(message = "Account Role required!")
        String role
) {
}
