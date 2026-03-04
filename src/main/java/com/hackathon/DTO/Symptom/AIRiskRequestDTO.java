package com.hackathon.DTO.Symptom;

import lombok.Data;

@Data
public class AIRiskRequestDTO {
    private Long patientId;
    private double riskProbability;
}
