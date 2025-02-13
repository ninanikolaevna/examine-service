package com.example.proskycourse2.examineservice.controller;

import com.example.proskycourse2.examineservice.domain.Question;
import com.example.proskycourse2.examineservice.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public class ExamController {
    private final ExaminerService examService;


    @Autowired
    public ExamController(ExaminerService examService) {
                this.examService = examService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examService.getQuestions(amount);
    }
}
