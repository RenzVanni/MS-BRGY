package com.ms_spring_brgy.healthAndSanitation.model;

import com.ms_spring_brgy.healthAndSanitation.enums.Action;
import com.ms_spring_brgy.healthAndSanitation.enums.Concern;
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
@Table(name = "health_and_sanitation")
public class HAS_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reporting_name;
    private String reporting_contactNo;

    private LocalDate date;
    private LocalTime time;
    private String location;

    @Column(columnDefinition = "Text")
    private String description;

    @Column(columnDefinition = "Text")
    private String condition;

    @Column(columnDefinition = "Text")
    private String recommendations;

    @Column(columnDefinition = "Text")
    private String endorsement;

    @Enumerated(EnumType.STRING)
    private Concern concern;

    @Enumerated(EnumType.STRING)
    private Action action;
}
