package com.ms_spring_brgy.incident.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Action {
    INCIDENT_LOGGING("Logging the Incident in the Barangay Blotter"),
    CONDUCT_INVESTIGATION("Conducting Initial Investigation"),
    MEDIATE_DISPUTE("Mediating Dispute or Conflict"),
    ISSUE_BLOTTER_REPORT("Issuing Barangay Blotter or Certification"),
    REFER_TO_PNP("Referral to Philippine National Police"),
    REFER_TO_FIRE_DEPARTMENT("Referral to Bureau of Fire Protection"),
    REFER_TO_HEALTH_CENTER("Referral to Barangay or Municipal Health Center"),
    REFER_TO_MSWDO("Referral to Municipal Social Welfare and Development Office"),
    REFER_TO_HIGHER_AUTHORITY("Referral to City/Municipal Hall or Other LGU Units"),
    ARREST_OR_APPREHEND("Apprehending or Assisting in Arrest of Suspect (if applicable)"),
    PROVIDE_FIRST_AID("Providing First Aid or Immediate Medical Assistance"),
    ACTIVATE_EMERGENCY_RESPONSE("Activating Barangay Emergency Response Team"),
    CONDUCT_CLEAN_UP("Conducting Clean-up or Clearing Operation"),
    HOLD_COMMUNITY_ASSEMBLY("Holding Community Assembly or Counseling"),
    ISSUE_NOTICE_OR_WARNING("Issuing Warning or Ordinance Violation Notice"),
    MONITOR_AND_FOLLOW_UP("Monitoring and Follow-up of Incident Status");

    private final String label;
}
