package com.raj.quizApp.Repository;

import com.raj.quizApp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
