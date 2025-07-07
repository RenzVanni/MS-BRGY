package com.ms_spring_brgy.incident.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Incident_Type {
    // Crime-related
    THEFT("Theft or Robbery"),
    ASSAULT("Physical Assault"),
    DOMESTIC_VIOLENCE("Domestic Violence"),
    ILLEGAL_DRUGS("Illegal Drugs"),
    VANDALISM("Vandalism"),
    TRESPASSING("Trespassing"),

    // Disaster and Emergency
    FIRE("Fire"),
    FLOOD("Flood"),
    EARTHQUAKE("Earthquake"),
    TYPHOON("Typhoon"),
    LANDSLIDE("Landslide"),
    STRUCTURAL_COLLAPSE("Structural Collapse"),

    // Health and Sanitation
    DISEASE_OUTBREAK("Disease Outbreak"),
    FOOD_POISONING("Food Poisoning"),
    WATER_CONTAMINATION("Unsafe Water Contamination"),
    ANIMAL_BITE("Animal Bite (e.g., Dog Bite)"),

    // Public Disturbance
    NOISE_COMPLAINT("Noise Complaint"),
    CURFEW_VIOLATION("Curfew Violation"),
    ILLEGAL_GATHERING("Illegal Gathering"),
    DRUNKEN_BEHAVIOR("Drunken Behavior"),

    // Traffic and Road
    VEHICULAR_ACCIDENT("Vehicular Accident"),
    ILLEGAL_PARKING("Illegal Parking"),
    ROAD_OBSTRUCTION("Road or Sidewalk Obstruction"),

    // Missing Persons
    LOST_CHILD("Lost Child"),
    MISSING_PERSON("Missing Person"),

    // Environmental
    ILLEGAL_DUMPING("Illegal Dumping of Garbage"),
    OPEN_BURNING("Open Burning"),
    POLLUTION("Water or Air Pollution"),

    // Barangay Ordinance Violations
    ANTI_LITTERING("Anti-littering Violation"),
    ANTI_SMOKING("Anti-smoking Violation"),
    CURFEW_NON_COMPLIANCE("Non-compliance with Curfew or Ordinance"),

    // Civil or Community Disputes
    BOUNDARY_DISPUTE("Boundary Dispute"),
    NEIGHBOR_COMPLAINT("Neighbor Complaint"),
    FAMILY_FEUD("Family Feud / Barangay Mediation Case");

    private final String label;
}
