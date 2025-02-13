package com.example.proskycourse2.examineservice;

import com.example.proskycourse2.examineservice.domain.Question;
import com.example.proskycourse2.examineservice.exception.TooManyQuestionsRequestedException;
import com.example.proskycourse2.examineservice.service.ExaminerServiceImpl;
import com.example.proskycourse2.examineservice.service.JavaQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collection;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestion_ShouldReturnCorrectAmountOfQuestions() {
                when(javaQuestionService.getAll()).thenReturn(Set.of(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"),
                                new Question("Вопрос 3", "Ответ 3")));
                when(javaQuestionService.getRandomQuestion()).thenReturn(
                                new Question("Вопрос 1", "Ответ 1"),
                                new Question("Вопрос 2", "Ответ 2"));
                Collection<Question> questions = out.getQuestions(2);
                assertEquals(2, questions.size());
                verify(javaQuestionService, times(2)).getRandomQuestion();

    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountIsInvalid() {
                assertThrows(IllegalArgumentException.class, () -> out.getQuestions(-1));
                assertThrows(IllegalArgumentException.class, () -> out.getQuestions(0));
                assertThrows(TooManyQuestionsRequestedException.class, () -> out.getQuestions(4));
    }

    @ParameterizedTest
    @CsvSource({"0, 5", "-1, 10"})
    void validateAmount_ShouldThrowIllegalArgumentException_WhenAmountIsZeroOrNegative(int amount, int maxSize) {
                assertThrows(IllegalArgumentException.class, () -> out.validateAmount(amount, maxSize));
    }

    @ParameterizedTest
    @CsvSource({"6, 5", "11, 10"})
    void validateAmount_ShouldThrowTooManyQuestionsRequestedException_WhenAmountExceedsMaxSize(int amount, int maxSize) {
                assertThrows(TooManyQuestionsRequestedException.class, () -> out.validateAmount(amount, maxSize));
    }

    @ParameterizedTest
    @CsvSource({"1, 5", "3, 10", "10, 10"})
    void validateAmount_ShouldNotThrowException_WhenAmountIsValid(int amount, int maxSize) {
                assertDoesNotThrow(() -> out.validateAmount(amount, maxSize));
    }
}
