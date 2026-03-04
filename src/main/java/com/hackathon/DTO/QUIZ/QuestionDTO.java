package com.hackathon.DTO.QUIZ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private String code;              // unique question id
    private String question;
    private List<OptionDTO> options;
}
