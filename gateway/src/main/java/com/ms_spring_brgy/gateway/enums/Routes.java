package com.ms_spring_brgy.gateway.enums;

public enum Routes {
    ACCOUNT("account-service"),
    RESIDENTS("residents-service"),
    OFFICIALS("officials-service"),
    BLOTTER("blotter-service"),
    COMPLAINT("complaint-service"),
    DAE("disasterAndEmergency-service"),
    HAS("healthAndSanitation-service"),
    INCIDENT("incident-service");

    private final String label;

    Routes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
