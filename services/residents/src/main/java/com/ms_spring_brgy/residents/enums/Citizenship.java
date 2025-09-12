package com.ms_spring_brgy.residents.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Citizenship {
    FILIPINO("Filipino"),
    AMERICAN("American"),
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    INDIAN("Indian"),
    BRITISH("British"),
    CANADIAN("Canadian"),
    AUSTRALIAN("Australian"),
    GERMAN("German"),
    FRENCH("French"),
    SPANISH("Spanish"),
    VIETNAMESE("Vietnamese"),
    THAI("Thai"),
    INDONESIAN("Indonesian"),
    MALAYSIAN("Malaysian"),
    SINGAPOREAN("Singaporean"),
    PAKISTANI("Pakistani"),
    BANGLADESHI("Bangladeshi"),
    NIGERIAN("Nigerian"),
    SOUTH_AFRICAN("South African"),
    RUSSIAN("Russian"),
    ITALIAN("Italian"),
    BRAZILIAN("Brazilian"),
    MEXICAN("Mexico"),
    OTHERS("Others");

    private final String label;

    @JsonValue
    public String getLabel() {
        return label;
    }
}
