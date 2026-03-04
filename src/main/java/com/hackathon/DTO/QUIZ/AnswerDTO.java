package com.hackathon.DTO.QUIZ;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private String code;
    private int option;
}
