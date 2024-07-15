package com.raj.quizApp.Services;

import com.raj.quizApp.Model.Questions;
import com.raj.quizApp.Model.Quiz;
import com.raj.quizApp.Model.QuizWrapper;
import com.raj.quizApp.Model.Response;
import com.raj.quizApp.Repository.QuestionRepository;
import com.raj.quizApp.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String qTitle) {
            try {
                Quiz quiz=new Quiz();
                List<Questions> questionsList= questionRepository.findByCategoryRandomLimitNumQ(category,numQ);
                quiz.setTitle(qTitle);
                quiz.setQuestions(questionsList);
                quizRepo.save(quiz);
                return new ResponseEntity<>("success",HttpStatus.CREATED);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuizWrapper>> getQuizById(int id) {
        try {
            Optional<Quiz> quiz=quizRepo.findById(id);
            List<Questions> questions =quiz.get().getQuestions();
            List<QuizWrapper> quizWrappers=new ArrayList<QuizWrapper>();
            for(Questions q:questions){
                quizWrappers.add(new QuizWrapper(q.getId(),q.getCategory(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()));
            }
            return new ResponseEntity<>(quizWrappers,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateMarks(int id, List<Response> responses) {
        int TotalMarks=100;
        int marksObtained=0;
        List<Questions> questions=quizRepo.findById(id).get().getQuestions();

        for(Response r:responses){
            for(Questions q : questions){
                if(r.getResponse().equals(q.getAns()))
                    marksObtained+=1;
            }

        }
        return new ResponseEntity<>(marksObtained,HttpStatus.OK);


    }
}
