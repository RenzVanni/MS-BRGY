package com.ms_spring_brgy.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateUserRequestDTO(
        String id,

        @NotBlank(message = "Username required!")
        String username,

        @NotBlank(message = "Email required!")
        String email,

        @NotNull(message = "Resident ID cannot be null!")
        Long residentId,

        @Size(min = 1, message = "Provide at least one role")
        List<String> role
) {
}
