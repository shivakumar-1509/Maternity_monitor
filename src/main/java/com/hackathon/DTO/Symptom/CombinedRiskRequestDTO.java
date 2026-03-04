package com.hackathon.DTO.Symptom;

import com.hackathon.model.SeverityLevel;
import lombok.Data;

import java.util.Set;

@Data
public class CombinedRiskRequestDTO {

    private Long patientId;
    private double aiProbability;
    private Set<SymptomDTO> symptoms;

    @Data
    public static class SymptomDTO {
        private String name;
        private SeverityLevel severity;
    }
}