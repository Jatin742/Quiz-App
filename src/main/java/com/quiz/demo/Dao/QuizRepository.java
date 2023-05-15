package com.quiz.demo.Dao;

import com.quiz.demo.Entity.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuizRepository extends MongoRepository<Quiz,Integer> {

    List<Quiz> findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Quiz.Status status,LocalDateTime startDate, LocalDateTime endDate);

    Quiz findById(int id);

    List<Quiz> findAll();
}
