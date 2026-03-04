package com.hackathon.service;

import com.hackathon.Repository.PatientRepo;
import com.hackathon.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Patient savePatient(Patient patient) {
        return patientRepo.save(patient);
    }
}
