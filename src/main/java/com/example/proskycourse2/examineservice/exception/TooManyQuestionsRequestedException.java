package com.example.proskycourse2.examineservice.exception;

public class TooManyQuestionsRequestedException extends RuntimeException{
    public TooManyQuestionsRequestedException(String message) {
        super(message);
    }
}
