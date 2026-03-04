package com.hackathon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vitals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double height;
    private float weight;
    private float bmi;

    private float systolic;
    private float diastolic;
    private float heartRate;
    private float bodyTemperature;
    private float bloodSugar;

    private float protein;
    private float ironMg;
    private float dietScore;

    private LocalDateTime recordedAt;

    private float RiskScore;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
