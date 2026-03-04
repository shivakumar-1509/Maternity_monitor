package com.hackathon.service;

import com.hackathon.DTO.AI.AiModelInput;
import org.springframework.stereotype.Service;

@Service
public class AIpredictionService {
    public double predictRisk(AiModelInput input) {

        // Dummy ML logic
        double score = (
                input.getBmi() * 0.1 +
                        input.getSystolic() * 0.01 +
                        input.getBloodSugar() * 0.01
        ) / 10;

        return Math.min(score, 1.0);
    }
}
