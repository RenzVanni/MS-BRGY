package com.ms_spring_brgy.complaint.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Complaint_Relief {
    APOLOGY("Apology"),
    PUBLIC_APOLOGY("Public Apology"),
    RETURN_OF_PROPERTY("Return of Property / Item"),
    PAYMENT_OF_DAMAGES("Payment of Damages / Debt"),
    CEASE_AND_DESIST("Cease and Desist Order"),
    BOUNDARY_ADJUSTMENT("Boundary Adjustment / Agreement"),
    CUSTODY_AGREEMENT("Custody / Visitation Agreement"),
    BARANGAY_PROTECTION_ORDER("Barangay Protection Order (BPO)"),
    SETTLEMENT_AGREEMENT("Settlement Agreement / Mediation"),
    COMMUNITY_SERVICE("Community Service"),
    REFERRAL_TO_HIGHER_AUTHORITY("Referral to Higher Authority");

    private final String label;
}
