package com.quiz.demo.Controller;

import com.quiz.demo.Entity.Quiz;
import com.quiz.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private QuizService quizService;
    @PostMapping("/quizzes")
    public ResponseEntity<Object> addQuiz(@RequestBody Quiz quiz){
        try{
            return new ResponseEntity<>(quizService.addQuiz(quiz),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(quiz,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/quizzes/active")
    public ResponseEntity<List<Quiz>> getQuizes(){
        return new ResponseEntity<>(quizService.getQuizes(),HttpStatus.OK);
    }

    @GetMapping("/quizzes/{id}/result")
    public ResponseEntity<Quiz.Status> getResult(@PathVariable int id){
        return new ResponseEntity<>(quizService.getResult(id),HttpStatus.OK);
    }
    @GetMapping("/quizzes/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return new ResponseEntity<>(quizService.getAllQuizzes(),HttpStatus.OK);
    }
}
