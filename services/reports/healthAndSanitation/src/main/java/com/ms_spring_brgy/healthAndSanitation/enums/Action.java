package com.ms_spring_brgy.healthAndSanitation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Action {
    HEALTH_MONITORING("Health Monitoring and Disease Surveillance"),
    CLEAN_UP_DRIVE("Clean-up Drives and Community Cleanliness Campaigns"),
    WASTE_SEGREGATION("Implementation of Waste Segregation and Disposal Ordinances"),
    WATER_TESTING("Water Quality Testing and Chlorination"),
    NUTRITION_PROGRAMS("Feeding and Nutrition Programs"),
    VACCINATION_CAMPAIGNS("Vaccination and Immunization Campaigns"),
    HOUSE_TO_HOUSE_VISITS("House-to-House Health Checkups by BHWs"),
    VECTOR_CONTROL("Fogging and Mosquito Breeding Site Elimination"),
    HEALTH_EDUCATION("Health Education and Hygiene Promotion"),
    REFERRAL_SERVICES("Referral of Critical Cases to Health Centers or Hospitals"),
    PSYCHOSOCIAL_SUPPORT("Mental Health and Psychosocial Support"),
    COORDINATION_WITH_LGUS("Coordination with LGUs and Health Agencies");

    private final String label;
}
