package com.ms_spring_brgy.PDF_Builder.model;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Resident_Model {
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private LocalDate birth_date;
    private String birth_place;
    private String address;
    private String contact_no;
    private Boolean voter_status;
    private String citizenship;
    private Boolean PWD;
    private Boolean OSY;
    private String civil_status;
    private Long official_id;
    private Long account_id;
    private String profile_image_url;
}
