package com.quiz.demo.Service;

import com.quiz.demo.Dao.QuizRepository;
import com.quiz.demo.Entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository repository;
    public Quiz addQuiz(Quiz quiz) {
        if(repository.findById(quiz.getId())!=null){
            throw new RuntimeException("Id already Used");
        }
        if(quiz.getOption().length!=4){
            throw new RuntimeException("Invalid Options");
        }
        if(quiz.getStartDate().compareTo(quiz.getEndDate())>0){
            throw new RuntimeException("Invalid Dates");
        }
        check(quiz);
        return quiz;
    }
    public List<Quiz> getQuizes(){
        LocalDateTime currentDate = LocalDateTime.now();
        return repository.findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual( Quiz.Status.ACTIVE,currentDate, currentDate);
    }
    public Quiz.Status getResult(int id){
        Quiz quiz=repository.findById(id);
        check(quiz);
        return quiz.getStatus();
    }
    public List<Quiz> getAllQuizzes(){
        List<Quiz> quizzes= repository.findAll();
        for(Quiz quiz:quizzes){
            check(quiz);
        }
        return quizzes;
    }
    public void check(Quiz quiz){
        LocalDateTime start=quiz.getStartDate();
        LocalDateTime end=quiz.getEndDate();
        LocalDateTime date=LocalDateTime.now();
        int comparison1=start.compareTo(date);
        int comparison2=end.compareTo(date);

        if(comparison1<=0 && comparison2>=0){
            quiz.setStatus(Quiz.Status.ACTIVE);
        }
        else if(comparison1>0){
            quiz.setStatus(Quiz.Status.INACTIVE);
        }
        else{
            quiz.setStatus(Quiz.Status.FINISHED);
        }
        repository.save(quiz);
    }
}
