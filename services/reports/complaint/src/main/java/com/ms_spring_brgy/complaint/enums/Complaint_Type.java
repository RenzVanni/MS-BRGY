package com.ms_spring_brgy.complaint.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Complaint_Type {
    VERBAL_ABUSE("Verbal Abuse"),
    PHYSICAL_ASSAULT("Physical Assault"),
    THEFT_MINOR("Theft (Minor)"),
    TRESPASSING("Trespassing"),
    BOUNDARY_DISPUTE("Boundary Dispute"),
    NOISE_COMPLAINT("Noise Complaint"),
    VANDALISM("Vandalism"),
    PUBLIC_SCANDAL("Public Scandal"),
    CHILD_CUSTODY("Child Custody / Family Issue"),
    NON_PAYMENT_OF_DEBT("Non-payment of Debt"),
    ANIMAL_NUISANCE("Animal Nuisance"),
    ILLEGAL_PARKING("Illegal Parking"),
    CURFEW_VIOLATION("Curfew Violation"),
    GARBAGE_DISPOSAL("Garbage Disposal Issue"),
    CYBERBULLYING("Cyberbullying / Online Harassment"),
    COMPLAINT_AGAINST_OFFICIAL("Complaint Against Official");

    private final String label;
}
