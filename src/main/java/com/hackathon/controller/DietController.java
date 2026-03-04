package com.hackathon.controller;

import com.hackathon.DTO.QUIZ.DietResultResponseDTO;
import com.hackathon.DTO.QUIZ.DietSubmissionDTO;
import com.hackathon.DTO.QUIZ.QuestionDTO;
import com.hackathon.service.Diet.DietResultService;
import com.hackathon.service.Diet.Diet_Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/diet/")
public class DietController {
    public Diet_Score questionService;

    public DietResultService dietService;

    @Autowired
    public DietController(DietResultService dietService, Diet_Score questionService) {
        this.dietService = dietService;
        this.questionService = questionService;
    }

    // 🔹 Get all quiz questions
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    // 🔹 Submit quiz answers
    @PostMapping("/submit")
    public ResponseEntity<DietResultResponseDTO> submitQuiz(
            @RequestBody DietSubmissionDTO request) {

        DietResultResponseDTO result =
                dietService.calculate(request);

        return ResponseEntity.ok(result);
    }


}
