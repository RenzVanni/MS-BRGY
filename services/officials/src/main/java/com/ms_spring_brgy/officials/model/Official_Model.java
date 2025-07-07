package com.ms_spring_brgy.officials.model;

import com.ms_spring_brgy.officials.enums.Position;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "officials")
public class Official_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate term_start;
    private LocalDate term_end;

    private Long resident_id;

    @Enumerated(EnumType.STRING)
    private Position position;
}
