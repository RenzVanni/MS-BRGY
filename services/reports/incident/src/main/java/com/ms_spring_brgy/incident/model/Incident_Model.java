package com.ms_spring_brgy.incident.model;

import com.ms_spring_brgy.incident.enums.Action;
import com.ms_spring_brgy.incident.enums.Incident_Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incident")
public class Incident_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String complainant_name;
    private LocalDate complainant_birthdate;
    private String complainant_address;
    private String complainant_contactNo;

    private String respondent_name;
    private LocalDate respondent_birthdate;
    private String respondent_address;
    private String respondent_contactNo;

    private String witness_name;
    private LocalDate witness_birthdate;
    private String witness_address;
    private String witness_contactNo;

    @Column(columnDefinition = "Text")
    private String settlement;

    @Column(columnDefinition = "Text")
    private String recommendations;

    private LocalDate date;
    private LocalTime time;
    private String Location;

    @Enumerated(EnumType.STRING)
    private Incident_Type type;

    @Enumerated(EnumType.STRING)
    private Action action;

    private Long official_id;
}
