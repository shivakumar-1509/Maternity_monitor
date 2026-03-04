package com.hackathon.DTO.Symptom;

import com.hackathon.DTO.SymptomDTO;
import com.hackathon.model.SeverityLevel;
import com.hackathon.model.Symptom;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class RiskAssessmentRequestDTO {

    private Long patientId;
    private Set<SymptomDTO> symptoms;



}
