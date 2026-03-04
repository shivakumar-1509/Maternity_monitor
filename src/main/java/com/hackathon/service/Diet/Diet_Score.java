package com.hackathon.service.Diet;

import com.hackathon.DTO.QUIZ.OptionDTO;
import com.hackathon.DTO.QUIZ.QuestionDTO;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class Diet_Score {

    public List<QuestionDTO> getQuestions(){

        return List.of(

                new QuestionDTO(
                        "mealFrequency",
                        "In a normal day, how many times do you eat?",
                        List.of(
                                new OptionDTO(1,"3 meals and snacks",10),
                                new OptionDTO(2,"3 meals",7),
                                new OptionDTO(3,"2 meals",3),
                                new OptionDTO(4,"Skip meals",0)
                        )
                ),

                new QuestionDTO(
                        "appetiteLoss",
                        "Do you stay hungry because you don’t feel like eating?",
                        List.of(
                                new OptionDTO(1,"Never",10),
                                new OptionDTO(2,"Sometimes",7),
                                new OptionDTO(3,"Often",3),
                                new OptionDTO(4,"Almost daily",0)
                        )
                ),

                new QuestionDTO(
                        "proteinIntake",
                        "How often do you eat egg, dal, milk, paneer or chicken?",
                        List.of(
                                new OptionDTO(1,"2–3 times daily",10),
                                new OptionDTO(2,"Once daily",7),
                                new OptionDTO(3,"Few times a week",3),
                                new OptionDTO(4,"Rarely",0)
                        )
                ),

                new QuestionDTO(
                        "milkIntake",
                        "Do you drink milk?",
                        List.of(
                                new OptionDTO(1,"Twice daily",10),
                                new OptionDTO(2,"Once daily",7),
                                new OptionDTO(3,"Few days/week",3),
                                new OptionDTO(4,"Never",0)
                        )
                ),

                new QuestionDTO(
                        "leafyVegetables",
                        "How often do you eat green leafy vegetables (spinach, soppu)?",
                        List.of(
                                new OptionDTO(1,"Daily",10),
                                new OptionDTO(2,"Alternate days",7),
                                new OptionDTO(3,"Weekly",3),
                                new OptionDTO(4,"Rarely",0)
                        )
                ),

                new QuestionDTO(
                        "ironFoods",
                        "Do you eat jaggery, dates, or groundnuts?",
                        List.of(
                                new OptionDTO(1,"Daily",10),
                                new OptionDTO(2,"Few times/week",7),
                                new OptionDTO(3,"Sometimes",3),
                                new OptionDTO(4,"Never",0)
                        )
                ),

                new QuestionDTO(
                        "teaAfterMeal",
                        "Do you drink tea or coffee immediately after meals?",
                        List.of(
                                new OptionDTO(1,"Never",10),
                                new OptionDTO(2,"Sometimes",7),
                                new OptionDTO(3,"Often",3),
                                new OptionDTO(4,"Always",0)
                        )
                ),

                new QuestionDTO(
                        "fruitIntake",
                        "How often do you eat fruits?",
                        List.of(
                                new OptionDTO(1,"Daily",10),
                                new OptionDTO(2,"3–4 times/week",7),
                                new OptionDTO(3,"Weekly",3),
                                new OptionDTO(4,"Rarely",0)
                        )
                ),

                new QuestionDTO(
                        "vitaminCWithMeals",
                        "Do you take lemon or citrus fruits with meals?",
                        List.of(
                                new OptionDTO(1,"Daily",10),
                                new OptionDTO(2,"Sometimes",7),
                                new OptionDTO(3,"Rarely",3),
                                new OptionDTO(4,"Never",0)
                        )
                ),

                new QuestionDTO(
                        "junkFood",
                        "How often do you eat fried or packaged foods (chips, bakery, fast food)?",
                        List.of(
                                new OptionDTO(1,"Rarely",10),
                                new OptionDTO(2,"Weekly",7),
                                new OptionDTO(3,"3–4 times/week",3),
                                new OptionDTO(4,"Daily",0)
                        )
                )
        );
    }
}
