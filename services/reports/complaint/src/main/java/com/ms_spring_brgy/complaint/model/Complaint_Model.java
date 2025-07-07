package com.ms_spring_brgy.complaint.model;

import com.ms_spring_brgy.complaint.enums.Complaint_Action;
import com.ms_spring_brgy.complaint.enums.Complaint_Relief;
import com.ms_spring_brgy.complaint.enums.Complaint_Type;
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
@Table(name = "complaint")
public class Complaint_Model {
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

    private LocalDate date;
    private LocalTime time;
    private String location;

    @Column(columnDefinition = "Text")
    private String statement_of_complaint;

    @Column(columnDefinition = "Text")
    private String evidence;

    private String witness_name;
    private LocalDate witness_birthdate;
    private String witness_address;
    private String witness_contactNo;

    @Enumerated(EnumType.STRING)
    private Complaint_Type type_id;

    @Enumerated(EnumType.STRING)
    private Complaint_Relief relief_id;

    @Enumerated(EnumType.STRING)
    private Complaint_Action action_id;
}
