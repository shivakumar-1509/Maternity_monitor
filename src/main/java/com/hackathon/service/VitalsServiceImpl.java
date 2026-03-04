package com.hackathon.service;

import com.hackathon.DTO.*;
import com.hackathon.DTO.AI.AiModelInput;
import com.hackathon.Repository.PatientRepo;
import com.hackathon.Repository.VitalsRepository;
import com.hackathon.model.Patient;
import com.hackathon.model.Vitals;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class VitalsServiceImpl implements VitalsService {

    private final PatientRepo patientRepository;
    private final VitalsRepository vitalsRepository;
    private final AIpredictionService aiPredictionService;

    @Override
    public AIResponseDTO addVitals(NurseVitalsRequest request) {

        Patient patient = patientRepository.findById(Math.toIntExact(request.getPatientId()))
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        int age = calculateAge(patient.getDateOfBirth());

        float bmi = (float) (request.getWeight() /
                (request.getHeight() * request.getHeight()));

        AiModelInput aiInput = new AiModelInput(
                age,
                bmi,
                request.getSystolic(),
                request.getDiastolic(),
                request.getHeartRate(),
                request.getBodyTemperature(),
                request.getBloodSugar(),
                request.getProtein(),
                request.getIronMg()
        );

        double riskScore = aiPredictionService.predictRisk(aiInput);

        // Save vitals
        Vitals vitals = new Vitals();
        vitals.setPatient(patient);
        vitals.setHeight(request.getHeight());
        vitals.setWeight(request.getWeight());
        vitals.setBmi(bmi);
        vitals.setSystolic(request.getSystolic());
        vitals.setDiastolic(request.getDiastolic());
        vitals.setHeartRate(request.getHeartRate());
        vitals.setBodyTemperature(request.getBodyTemperature());
        vitals.setBloodSugar(request.getBloodSugar());
        vitals.setRiskScore((float) riskScore);
        vitals.setRecordedAt(LocalDateTime.now());

        vitalsRepository.save(vitals);

        // Build RiskDTO
        RiskDTO riskDTO = new RiskDTO(
                riskScore,
                getRiskLevel(riskScore)
        );

        // Build NutritionDTO (example logic)
        NutritionInputDTO nutritionDTO = new NutritionInputDTO(
                request.getProtein(),
                request.getIronMg()
        );

        // Build Final Response
        AIResponseDTO response = new AIResponseDTO();
        response.setPatient_id(Math.toIntExact(patient.getId()));
        response.setRisk(riskDTO);
        response.setNutrition(nutritionDTO);

        return response;
    }

    private int calculateAge(Date dateOfBirth) {
        LocalDate dob = dateOfBirth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private String getRiskLevel(double score) {
        if (score < 0.3) return "LOW";
        if (score < 0.7) return "MODERATE";
        return "HIGH";
    }
}
