package com.ms_spring_brgy.disasterAndEmergency.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Agencies {
    // National Government Agencies
    NDRRMC("Main coordinating body for national disaster risk reduction and response"),
    DILG("Oversees LGUs and ensures DRRM implementation and compliance"),
    DSWD("Distributes relief goods and provides evacuation support and psychosocial services"),
    DOH("Provides medical services and public health interventions"),
    AFP("Deploys troops and equipment for rescue, relief, and recovery"),
    PNP("Ensures law and order, assists in evacuation and security"),
    BFP("Responds to fires and assists in search and rescue operations"),
    PAGASA("Provides weather forecasts and typhoon warnings"),
    PHIVOLCS("Issues earthquake, tsunami, and volcanic eruption advisories"),
    MMDA("Handles flood control, traffic management, and emergency response in Metro Manila"),
    DEPED("Ensures safety of schools and facilitates use of classrooms as evacuation centers"),
    DPWH("Clears roads, repairs infrastructure, and ensures structural safety post-disaster"),

    // Local Government Units
    PROVINCIAL_DRRM("Coordinates disaster response at the provincial level"),
    CITY_MUNICIPAL_DRRM("Coordinates and supports barangay-level DRRM efforts"),
    BARANGAY_DRRM("First responder at the grassroots; leads localized preparedness and response"),
    HEALTH_CENTER("Provides medical aid and health monitoring during emergencies"),

    // Other Supporting Organizations
    RED_CROSS("Provides emergency assistance, blood services, and disaster relief"),
    NGO("Offers food, shelter kits, and community rehabilitation support"),
    MEDIA("Broadcasts warnings, advisories, and updates to the public"),
    FAITH_BASED_ORG("Offers shelter, food, and spiritual support"),
    PRIVATE_SECTOR("Provides logistics, funding, and technical assistance during disasters");

    private final String label;
}
