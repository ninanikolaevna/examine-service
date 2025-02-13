package com.example.proskycourse2.examineservice.service;

import com.example.proskycourse2.examineservice.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
