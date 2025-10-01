package com.ms_spring_brgy.officials.dto;

import com.ms_spring_brgy.officials.enums.Position;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateOfficialDTO(
        @NotNull(message = "ID is required!")
        int id,

        @NotNull(message = "Term start is required!")
        LocalDate term_start,

        @NotNull(message = "Term end is required!")
        @Future(message = "Term end must be in future")
        LocalDate term_end,

        @NotNull(message = "Resident ID is required")
        Long resident_id,

        @NotNull(message = "Position is required")
        Position position
) {
}
