package com.example.proskycourse2.examineservice.service;

import com.example.proskycourse2.examineservice.domain.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    //    private final Set<Question> usedQuestions = new HashSet<>();
    Random random = new Random();
    Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if ((question == null || answer == null) || (question.isEmpty() || answer.isEmpty())) {
            throw new IllegalArgumentException("Question and answer cannot be null");
        }
        questions.add(new Question(question, answer));
        return new Question(question, answer);
    }

    @Override
    public Question add(Question question) {
        if (question.isEmpty()) {
            throw new IllegalArgumentException("Question and answer cannot be null");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question) {
        return null;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions found");

        }
        List<Question> questionList = new ArrayList<>(questions);
        int randomIndex = random.nextInt(questionList.size());
        Question randomQuestion = questionList.get(randomIndex);
//        usedQuestions.add(randomQuestion);
        return randomQuestion;
    }
}
