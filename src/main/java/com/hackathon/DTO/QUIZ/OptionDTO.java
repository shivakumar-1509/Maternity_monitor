package com.hackathon.DTO.QUIZ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO{
    private int option;   // 1,2,3,4
    private String label;
    private int score;
}
