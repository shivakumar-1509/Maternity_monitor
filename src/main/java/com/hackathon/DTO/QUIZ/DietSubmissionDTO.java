package com.hackathon.DTO.QUIZ;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DietSubmissionDTO {
    private Long visitId;              // which visit
    private List<AnswerDTO> answers;
}
