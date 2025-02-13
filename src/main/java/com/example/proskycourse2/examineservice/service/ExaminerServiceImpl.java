package com.example.proskycourse2.examineservice.service;

import com.example.proskycourse2.examineservice.domain.Question;
import com.example.proskycourse2.examineservice.exception.TooManyQuestionsRequestedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
@Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();
        validateAmount(amount, allQuestions.size());
        Set<Question> randomQuestions = new HashSet<>();
        while (randomQuestions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            randomQuestions.add(randomQuestion);
        }
        return randomQuestions;
    }

    public void validateAmount(int amount, int maxSize) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        if (amount > maxSize) {
            throw new TooManyQuestionsRequestedException("amount must be less than maxSize");
        }
    }
}
