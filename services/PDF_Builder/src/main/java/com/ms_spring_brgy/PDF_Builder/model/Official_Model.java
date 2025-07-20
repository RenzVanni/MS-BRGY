package com.ms_spring_brgy.PDF_Builder.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
public class Official_Model {
    private int id;
    private LocalDate term_start;
    private LocalDate term_end;
    private Long resident_id;
    private String position;
}
