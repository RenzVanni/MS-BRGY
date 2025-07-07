package com.ms_spring_brgy.disasterAndEmergency.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DAE_Type {
    NATURAL_DISASTER("Natural Disasters"),
    MAN_MADE_DISASTER("Man-Made Disasters"),
    PUBLIC_HEALTH_EMERGENCIES("Public Health Emergencies"),
    ENVIRONMENTAL_EMERGENCIES("Environmental Emergencies");

    private final String label;
}
