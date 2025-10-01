package com.ms_spring_brgy.officials.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Position {
    PUNONG_BARANGAY("Punong Barangay"),
    BARANGAY_KAGAWAD("Barangay Kagawad"),
    SK_CHAIRPERSON("SK Chairperson"),
    BARANGAY_SECRETARY("Barangay Secretary"),
    BARANGAY_TREASURER("Barangay Treasurer");

    @JsonValue
    private final String label;
}
