package com.hackathon.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class AIResponseDTO {

    private int patient_id;
    private RiskDTO risk;
    private NutritionInputDTO nutrition;


}
