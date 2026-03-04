package com.hackathon.controller;

import com.hackathon.DTO.AI.AIModelResponse;
import com.hackathon.DTO.AI.AiModelInput;
import com.hackathon.DTO.AIResponseDTO;
import com.hackathon.Repository.PatientRepo;
import com.hackathon.model.Patient;
import com.hackathon.service.AIService;
import com.hackathon.service.RiskAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin("")
@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/predict")
    public AIResponseDTO predict(@RequestBody Object requestBody) {
        return aiService.processPrediction(requestBody);
    }


        @Autowired
        private PatientRepo patientRepo;

        @Autowired
        private RiskAssessmentService riskService;

        private RestTemplate restTemplate = new RestTemplate();

        private final String FASTAPI_URL = "http://localhost:8000/predict";

        @PostMapping("/assess/{patientId}")
        public AIModelResponse assessRisk(@PathVariable Long patientId) {

            // 1️⃣ Get patient
            Patient patient = patientRepo.findById(Math.toIntExact(patientId))
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            // 2️⃣ Build AI input
            AiModelInput input = riskService.buildAiInput(patient);

            // 3️⃣ Send to FastAPI
            AIModelResponse response = restTemplate.postForObject(
                    FASTAPI_URL,
                    input,
                    AIModelResponse.class
            );

            return response;
        }
    }

