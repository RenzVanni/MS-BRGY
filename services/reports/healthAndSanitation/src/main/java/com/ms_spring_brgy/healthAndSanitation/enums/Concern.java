package com.ms_spring_brgy.healthAndSanitation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Concern {
    COMMUNICABLE_DISEASES("Communicable Diseases"),
    SANITATION_AND_WASTE_MANAGEMENT("Sanitation and Waste Management"),
    SAFE_WATER_SUPPLY("Safe Water Supply"),
    MALNUTRITION_AND_MATERNAL_HEALTH("Malnutrition and Maternal Health"),
    HYGIENE_PRACTICES("Hygiene Practices"),
    VECTOR_AND_PEST_CONTROL("Vector and Pest Control"),
    BARANGAY_HEALTH_CENTER_CAPACITY("Barangay Health Center Capacity"),
    MENTAL_HEALTH_AND_SUBSTANCE_ABUSE("Mental Health and Substance Abuse"),
    ENVIRONMENTAL_HEALTH("Environmental Health");

    private final String label;
}
