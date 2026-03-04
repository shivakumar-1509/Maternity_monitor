package com.hackathon.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severity;

    @ManyToOne
    private Patient patient;

    private String status;
}
