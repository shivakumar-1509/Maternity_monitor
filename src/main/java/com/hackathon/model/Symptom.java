package com.hackathon.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Symptom {


        HEAVY_VAGINAL_BLEEDING(SeverityLevel.IMMEDIATE),
        SEVERE_ABDOMINAL_PAIN(SeverityLevel.IMMEDIATE),
        SEIZURES(SeverityLevel.IMMEDIATE),
        DIFFICULTY_BREATHING(SeverityLevel.IMMEDIATE),
        SEVERE_HEADACHE_VISION(SeverityLevel.IMMEDIATE),
        SUDDEN_SWELLING_FACE_HANDS(SeverityLevel.IMMEDIATE),
        HIGH_FEVER(SeverityLevel.IMMEDIATE),
        NO_BABY_MOVEMENT(SeverityLevel.IMMEDIATE),
        WATER_LEAKING(SeverityLevel.IMMEDIATE),
        FAINTING(SeverityLevel.IMMEDIATE),
        SEVERE_TRAUMA(SeverityLevel.IMMEDIATE),


        PERSISTENT_CRAMPS(SeverityLevel.URGENT),
        PRETERM_CONTRACTIONS(SeverityLevel.URGENT),
        BURNING_URINATION(SeverityLevel.URGENT),
        UNUSUAL_DISCHARGE(SeverityLevel.URGENT),
        SUDDEN_DIZZINESS(SeverityLevel.URGENT),
        CONTINUOUS_VOMITING(SeverityLevel.URGENT),
        SEVERE_ITCHING(SeverityLevel.URGENT),
        REDUCED_BABY_MOVEMENT(SeverityLevel.URGENT),


        MILD_SWELLING_FEET(SeverityLevel.SOON),
        MILD_CRAMPS(SeverityLevel.SOON),
        MILD_NAUSEA(SeverityLevel.SOON),
        MINOR_SPOTTING(SeverityLevel.SOON),
        MILD_HEADACHE(SeverityLevel.SOON);


        private final SeverityLevel severity;

        Symptom(SeverityLevel severity) {
            this.severity = severity;
        }


}
