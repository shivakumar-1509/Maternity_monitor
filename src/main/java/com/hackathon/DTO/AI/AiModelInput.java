package com.hackathon.DTO.AI;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AiModelInput {

    private int age;                 // derived from DOB
    private float bmi;               // calculated
    private float systolic;
    private float diastolic;
    private float heartRate;
    private float bodyTemperature;
    private float bloodSugar;
    private float protein;
    private float ironMg;
}
