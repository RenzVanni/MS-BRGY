package com.ms_spring_brgy.gateway.enums;

public enum Paths {
    ACCOUNT("/api/v1/accounts/**"),
    RESIDENTS("/api/v1/residents/**"),
    OFFICIALS("/api/v1/officials/**"),
    BLOTTER("/api/v1/blotter/**"),
    COMPLAINT("/api/v1/complaint/**"),
    DAE("/api/v1/disasterAndEmergency/**"),
    HAS("/api/v1/healthAndSanitation/**"),
    INCIDENT("/api/v1/incident/**");

    private final String label;

    Paths(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
