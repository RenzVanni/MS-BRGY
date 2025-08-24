package com.ms_spring_brgy.gateway.enums;

public enum URI {
    ACCOUNT("lb:http://" + Routes.ACCOUNT.getLabel()),
    RESIDENTS("lb:http://" + Routes.RESIDENTS.getLabel()),
    OFFICIALS("lb:http://" + Routes.OFFICIALS.getLabel()),
    BLOTTER("lb:http://" + Routes.BLOTTER.getLabel()),
    COMPLAINT("lb:http://" + Routes.COMPLAINT.getLabel()),
    DAE("lb:http://" + Routes.DAE.getLabel()),
    HAS("lb:http://" + Routes.HAS.getLabel()),
    INCIDENT("lb:http://" + Routes.INCIDENT.getLabel());

    private final String label;

    URI(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
