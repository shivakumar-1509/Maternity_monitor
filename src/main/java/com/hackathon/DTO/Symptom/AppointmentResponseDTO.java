package com.hackathon.DTO.Symptom;



import com.hackathon.model.SeverityLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {

    private Long appointmentId;
    private String patientName;
    private SeverityLevel severity;
    private LocalDate appointmentDate;
    private String status;
}
