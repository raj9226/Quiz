package com.raj.quizApp.Controller;


import com.raj.quizApp.Model.Quiz;
import com.raj.quizApp.Model.QuizWrapper;
import com.raj.quizApp.Model.Response;
import com.raj.quizApp.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String qTitle){
        return quizService.createQuiz(category,numQ,qTitle);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuizById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateMarks(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.calculateMarks(id,responses);
    }
}
