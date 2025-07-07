package com.ms_spring_brgy.residents.model;

import com.ms_spring_brgy.residents.enums.Citizenship;
import com.ms_spring_brgy.residents.enums.Civil_Status;
import com.ms_spring_brgy.residents.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "residents")
public class Resident_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstname;
    private String middlename;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth_date;
    private String birth_place;

    @Column(nullable = false)
    private String address;

    private String contact_no;
    private Boolean voter_status;

    @Enumerated(EnumType.STRING)
    private Citizenship citizenship;

    private Boolean PWD;
    private Boolean OSY;

    @Enumerated(EnumType.STRING)
    private Civil_Status civil_status;

    private Long official_id;
    private Long account_id;

    private String profile_image_url;
}
