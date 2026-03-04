package com.hackathon.controller;



import com.hackathon.DTO.Symptom.RiskAssessmentRequestDTO;
import com.hackathon.DTO.Symptom.AppointmentResponseDTO;
import com.hackathon.Repository.PatientRepo;
import com.hackathon.model.Appointment;
import com.hackathon.model.Patient;
import com.hackathon.service.RiskAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
public class RiskAssesmentController {

    private final RiskAssessmentService riskAssessmentService;
    private final PatientRepo patientRepo;

    @Autowired
    public RiskAssesmentController(RiskAssessmentService riskAssessmentService,
                                    PatientRepo patientRepo) {
        this.riskAssessmentService = riskAssessmentService;
        this.patientRepo = patientRepo;
    }

    @PostMapping("/assess")
    public ResponseEntity<AppointmentResponseDTO> assessRisk(
            @RequestBody RiskAssessmentRequestDTO request) {
        int id = Math.toIntExact(request.getPatientId());
        // 🔹 Fetch patient
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // 🔹 Process symptoms
        Appointment appointment =
                riskAssessmentService.processSymptoms(
                        request.getSymptoms(),
                        patient
                );

        // 🔹 Build response
        AppointmentResponseDTO response = new AppointmentResponseDTO(
                appointment.getId(),
                patient.getName(),
                appointment.getSeverity(),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        );

        return ResponseEntity.ok(response);
    }
}