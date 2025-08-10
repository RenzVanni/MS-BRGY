package com.ms_spring_brgy.gateway.enums;

public enum URI {
    ACCOUNT("lb:http://" + Routes.ACCOUNT),
    RESIDENTS("lb:http://" + Routes.RESIDENTS),
    OFFICIALS("lb:http://" + Routes.OFFICIALS),
    BLOTTER("lb:http://" + Routes.BLOTTER),
    COMPLAINT("lb:http://" + Routes.COMPLAINT),
    DAE("lb:http://" + Routes.DAE),
    HAS("lb:http://" + Routes.HAS),
    INCIDENT("lb:http://" + Routes.INCIDENT);

    private final String label;

    URI(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
