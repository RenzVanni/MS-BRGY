package com.ms_spring_brgy.gateway.enums;

public enum URI {
    ACCOUNT("lb://" + Routes.ACCOUNT.getLabel()),
    RESIDENTS("lb://" + Routes.RESIDENTS.getLabel()),
    OFFICIALS("lb://" + Routes.OFFICIALS.getLabel()),
    BLOTTER("lb://" + Routes.BLOTTER.getLabel()),
    COMPLAINT("lb://" + Routes.COMPLAINT.getLabel()),
    DAE("lb://" + Routes.DAE.getLabel()),
    HAS("lb://" + Routes.HAS.getLabel()),
    INCIDENT("lb://" + Routes.INCIDENT.getLabel());

    private final String label;

    URI(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
