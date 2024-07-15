package com.raj.quizApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizWrapper {

    public Integer id;

    public String category;


    public String question;

    public String option1;

    public String option2;

    public String option3;

    public String option4;





}
