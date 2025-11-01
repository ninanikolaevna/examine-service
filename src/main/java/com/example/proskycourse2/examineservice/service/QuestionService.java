package com.example.proskycourse2.examineservice.service;

import com.example.proskycourse2.examineservice.domain.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(String question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
