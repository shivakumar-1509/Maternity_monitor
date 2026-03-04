package com.hackathon.service;

import com.hackathon.DTO.AIResponseDTO;
import com.hackathon.Repository.AppointmentRepo;
import com.hackathon.Repository.PatientRepo;
import com.hackathon.model.Appointment;
import com.hackathon.model.Patient;
import com.hackathon.model.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class AIService {


    private RestTemplate restTemplate;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    private final String FASTAPI_URL = "http://localhost:8000/predict";

    public AIResponseDTO processPrediction(Object requestBody) {


        AIResponseDTO response =
                restTemplate.postForObject(
                        FASTAPI_URL,
                        requestBody,
                        AIResponseDTO.class
                );

        // 2️⃣ Get Patient
        Patient patient = patientRepo.findById(response.getPatient_id()).orElseThrow(() -> new RuntimeException("Patient not found"));

        // 3️⃣ Extract probability
        double probability =
                response.getRisk().getRiskScore();

        // 4️⃣ Map probability to severity
        SeverityLevel severity = mapRiskToSeverity(probability);

        // 5️⃣ Calculate appointment date
        LocalDate appointmentDate =
                calculateAppointmentDate(severity);

        // 6️⃣ Create appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setSeverity(severity);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("SCHEDULED");

        appointmentRepo.save(appointment);

        return response;
    }

    // 🔹 Map probability
    private SeverityLevel mapRiskToSeverity(double probability) {

        if (probability >= 0.70)
            return SeverityLevel.IMMEDIATE;

        if (probability >= 0.40)
            return SeverityLevel.URGENT;

        if (probability >= 0.20)
            return SeverityLevel.SOON;

        return SeverityLevel.STANDARD;
    }

    // 🔹 Appointment date logic
    private LocalDate calculateAppointmentDate(SeverityLevel severity) {

        LocalDate today = LocalDate.now();

        return switch (severity) {
            case IMMEDIATE -> today.plusDays(1);
            case URGENT -> today.plusDays(3);
            case SOON -> today.plusWeeks(1);
            default -> today.plusWeeks(4);
        };
    }
}
