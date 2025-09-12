package com.ms_spring_brgy.residents.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }
}
