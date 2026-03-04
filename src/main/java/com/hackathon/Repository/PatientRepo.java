package com.hackathon.Repository;

import com.hackathon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {
    public Patient findByName(String username);
}
