package com.hackathon.service;



import com.hackathon.DTO.AI.AiModelInput;
import com.hackathon.DTO.SymptomDTO;
import com.hackathon.Repository.AppointmentRepo;
import com.hackathon.Repository.VitalsRepository;
import com.hackathon.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Comparator;
import java.util.Set;

@Service
public class RiskAssessmentService {

    private final AppointmentRepo appointmentRepo;
    private final VitalsRepository vitalsRepo;
    private final TwilioSmsService smsService;

    @Autowired
    public RiskAssessmentService(AppointmentRepo appointmentRepo,
                                 VitalsRepository vitalsRepo,
                                 TwilioSmsService smsService) {
        this.appointmentRepo = appointmentRepo;
        this.vitalsRepo = vitalsRepo;
        this.smsService = smsService;
    }

    // =============================
    // Evaluate Highest Severity
    // =============================
    public SeverityLevel evaluateSeverity(Set<SymptomDTO> symptoms) {

        if (symptoms == null || symptoms.isEmpty()) {
            return SeverityLevel.STANDARD;
        }

        return symptoms.stream()
                .map(SymptomDTO::getSeverity)
                .max(Comparator.comparingInt(SeverityLevel::getPriority))
                .orElse(SeverityLevel.STANDARD);
    }

    // =============================
    // Appointment Date Logic
    // =============================
    public LocalDate calculateAppointmentDate(SeverityLevel severity) {

        LocalDate today = LocalDate.now();

        return switch (severity) {
            case IMMEDIATE -> today.plusDays(1);
            case URGENT -> today.plusDays(3);
            case SOON -> today.plusWeeks(1);
            default -> today.plusWeeks(4);
        };
    }

    // =============================
    // Risk Mapping from AI
    // =============================
    public SeverityLevel mapRiskToSeverity(double probability) {

        if (probability >= 0.70)
            return SeverityLevel.IMMEDIATE;

        if (probability >= 0.40)
            return SeverityLevel.URGENT;

        if (probability >= 0.30)
            return SeverityLevel.SOON;

        return SeverityLevel.STANDARD;
    }

    // =============================
    // Process Symptoms & Create Appointment
    // =============================
    public Appointment processSymptoms(Set<SymptomDTO> symptoms, Patient patient) {

        SeverityLevel severity = evaluateSeverity(symptoms);
        LocalDate appointmentDate = calculateAppointmentDate(severity);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setSeverity(severity);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("SCHEDULED");

        appointmentRepo.save(appointment);

        notifyNurse(patient, severity, appointmentDate);

        return appointment;
    }

    // =============================
    // SMS Notification
    // =============================
    private void notifyNurse(Patient patient,
                             SeverityLevel severity,
                             LocalDate appointmentDate) {

        if (patient.getNurse() == null) return;

        String message = "ALERT: Patient "
                + patient.getName()
                + " reported "
                + severity
                + " symptoms. Appointment scheduled on "
                + appointmentDate;

        smsService.sendSms(
                patient.getNurse().getPhoneNumber(),
                message
        );
    }

    public AiModelInput buildAiInput(Patient patient) {

        // 1️⃣ Get vitals of patient
        Vitals vitals = vitalsRepo.findByPatient(patient)
                .orElseThrow(() -> new RuntimeException("Vitals not found"));

        // 2️⃣ Calculate age from Date of Birth
        LocalDate dob = patient.getDateOfBirth()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        int age = Period.between(dob, LocalDate.now()).getYears();

        // 3️⃣ Build AI input object
        return new AiModelInput(
                age,
                vitals.getBmi(),
                vitals.getSystolic(),
                vitals.getDiastolic(),
                vitals.getHeartRate(),
                vitals.getBodyTemperature(),
                vitals.getBloodSugar(),
                vitals.getProtein(),
                vitals.getIronMg()
        );
    }
}

