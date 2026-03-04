package com.hackathon.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskDTO {

    private double riskScore;
    private String riskLevel;
}
