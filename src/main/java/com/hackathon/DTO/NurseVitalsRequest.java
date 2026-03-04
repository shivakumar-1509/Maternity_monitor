package com.hackathon.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NurseVitalsRequest {

    private Long patientId;
    private double height;
    private float weight;
    private float systolic;
    private float diastolic;
    private float heartRate;
    private float bodyTemperature;
    private float bloodSugar;
    private float protein;
    private float ironMg;
}
