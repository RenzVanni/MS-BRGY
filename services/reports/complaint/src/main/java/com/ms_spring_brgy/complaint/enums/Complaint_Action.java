package com.ms_spring_brgy.complaint.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Complaint_Action {
    SUMMON_ISSUED("Summon Issued"),
    CONDUCTED_MEDIATION("Conducted Mediation"),
    MEDIATION_RESCHEDULED("Mediation Rescheduled"),
    WARNING_ISSUED("Warning Issued"),
    SETTLEMENT_AGREEMENT_SIGNED("Settlement Agreement Signed"),
    ADVISED_TO_ELEVATE("Advised to Elevate to Police"),
    ENDORSED_TO_POLICE("Endorsed to Police Station"),
    ENDORSED_TO_PROSECUTOR("Endorsed to Prosecutor or Court"),
    CASE_DISMISSED("Case Dismissed"),
    NO_SHOW_RESPONDENT("No Show by Respondent"),
    NO_SHOW_COMPLAINANT("No Show by Complainant"),
    BPO_ISSUED("Barangay Protection Order Issued"),
    COMPLAINANT_WITHDREW("Complainant Withdrew Complaint"),
    INFORMAL_SETTLEMENT("Case Settled Informally");

    private final String label;
}
