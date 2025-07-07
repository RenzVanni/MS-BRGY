package com.ms_spring_brgy.disasterAndEmergency.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Action_Taken {
    // PREPAREDNESS
    RISK_ASSESSMENT("Conduct hazard mapping and risk assessments"),
    INFORMATION_DRIVE("Educate residents through information campaigns and drills"),
    EMERGENCY_PLAN_CREATION("Prepare and update the Barangay DRRM Plan"),
    SUPPLY_STOCKPILING("Stockpile emergency supplies (food, water, medicine)"),
    LGU_COORDINATION("Coordinate with LGUs and national agencies"),

    // RESPONSE
    EARLY_WARNING("Disseminate warnings through megaphones, SMS, sirens"),
    EVACUATION("Lead and assist in evacuation to safe zones"),
    EMERGENCY_SERVICES("Provide first aid and maintain peace and order"),
    EOC_ACTIVATION("Activate Barangay Emergency Operations Center"),
    INCIDENT_REPORTING("Report situation to higher DRRM offices"),

    // RECOVERY
    DAMAGE_ASSESSMENT("Conduct rapid damage and needs assessment"),
    RELIEF_DISTRIBUTION("Distribute relief goods to affected residents"),
    PSYCHOSOCIAL_SUPPORT("Provide psychological first aid and counseling"),
    SERVICE_RESTORATION("Assist in restoring electricity, water, and sanitation"),
    REPORT_AND_EVALUATION("Document actions and update DRRM plans");

    private final String label;
}
