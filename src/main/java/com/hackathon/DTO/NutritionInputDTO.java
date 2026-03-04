package com.hackathon.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NutritionInputDTO {
    private float protein;
    private float ironMg;

    public NutritionInputDTO(float protein, float ironMg) {
        this.protein = protein;
        this.ironMg = ironMg;
    }
}
