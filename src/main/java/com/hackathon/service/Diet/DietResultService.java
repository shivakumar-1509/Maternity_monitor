package com.hackathon.service.Diet;

import com.hackathon.DTO.QUIZ.*;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietResultService {
    private final Diet_Score dietScoreService;

    public DietResultService(Diet_Score dietScoreService) {
        this.dietScoreService = dietScoreService;
    }

    public DietResultResponseDTO calculate(DietSubmissionDTO request) {

        List<QuestionDTO> questions = dietScoreService.getQuestions();

        int totalScore = 0;

        for (AnswerDTO answer : request.getAnswers()) {

            QuestionDTO question = questions.stream()
                    .filter(q -> q.getCode().equals(answer.getCode()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Invalid question code"));

            OptionDTO selectedOption = question.getOptions().stream()
                    .filter(o -> o.getOption() == answer.getOption())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Invalid option"));

            totalScore += selectedOption.getScore();
        }

        String riskLevel = determineRisk(totalScore);

        return new DietResultResponseDTO(totalScore, riskLevel);
    }

    private String determineRisk(int score) {

        if(score >= 80) return "GOOD";
        if(score >= 60) return "MODERATE";
        if(score >= 40) return "POOR";
        return "HIGH_RISK";
    }
}
