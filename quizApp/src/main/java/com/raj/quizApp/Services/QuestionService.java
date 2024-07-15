package com.raj.quizApp.Services;

import com.raj.quizApp.Model.Questions;
import com.raj.quizApp.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public void saveAll(List<Questions> questions) {
        questionRepository.saveAll(questions);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> save(Questions questions) {
        try {
             questionRepository.save(questions);
             return new ResponseEntity<>("success",HttpStatus.CREATED);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }
}
