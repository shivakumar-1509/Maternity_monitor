package com.hackathon.Repository;

import com.hackathon.model.Patient;
import com.hackathon.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VitalsRepository extends JpaRepository<Vitals, Long> {
    Optional<Vitals> findByPatient(Patient patient);
}
