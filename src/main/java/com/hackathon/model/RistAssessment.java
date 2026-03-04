package com.hackathon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class RistAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = Symptom.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "risk_assessment_symptoms",
            joinColumns = @JoinColumn(name = "risk_id"))
    @Column(name = "symptom")
    private Set<Symptom> selectedSymptoms;

    @Enumerated(EnumType.STRING)
    private SeverityLevel finalSeverity;

    @ManyToOne
    private Patient patient;

}
