package com.ms_spring_brgy.disasterAndEmergency.model;

import com.ms_spring_brgy.disasterAndEmergency.enums.Action_Taken;
import com.ms_spring_brgy.disasterAndEmergency.enums.Agencies;
import com.ms_spring_brgy.disasterAndEmergency.enums.DAE_Type;
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
@Table(name = "disaster_and_emergency")
public class DAE_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private LocalTime time;
    private String location;
    private String reporting_name;
    private String reporting_contactNo;

    @Column(columnDefinition = "Text")
    private String affected_area;

    private int injured;
    private int missing;
    private int displaced;
    private int casualties;

    private double damage_assessment;

    @Column(columnDefinition = "Text")
    private String needs_and_assistance;

    @Column(columnDefinition = "Text")
    private String follow_up_action;

    @Enumerated(EnumType.STRING)
    private DAE_Type type;

    @Enumerated(EnumType.STRING)
    private Action_Taken action_preparedness;

    @Enumerated(EnumType.STRING)
    private Action_Taken action_response;

    @Enumerated(EnumType.STRING)
    private Action_Taken action_recovery;

    @Enumerated(EnumType.STRING)
    private Agencies agencies;
}
