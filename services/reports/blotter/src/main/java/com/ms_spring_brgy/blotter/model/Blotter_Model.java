package com.ms_spring_brgy.blotter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "blotter")
public class Blotter_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String victim;
    private String complainant;
    private String respondent;
    private String location;
    private LocalTime time;
    private LocalDate date;

    @Column(columnDefinition = "Text")
    private String details;

    private String status;
    private String type;
}
