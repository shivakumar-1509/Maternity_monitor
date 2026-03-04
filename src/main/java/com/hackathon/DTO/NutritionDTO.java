package com.hackathon.DTO;




import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutritionDTO {

    private String deficiencyType;
    private double confidence;
    private List<String> recommendedFoods;

}