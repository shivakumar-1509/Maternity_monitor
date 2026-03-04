package com.hackathon.DTO.QUIZ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietResultResponseDTO {

    private int totalScore;
    private String riskLevel;
}
