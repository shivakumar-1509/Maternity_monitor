package com.hackathon.DTO.AI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIModelResponse {
    private double riskProbability;
    private double confidence;

}
