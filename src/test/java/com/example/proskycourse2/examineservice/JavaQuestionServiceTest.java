package com.example.proskycourse2.examineservice;

import com.example.proskycourse2.examineservice.domain.Question;
import com.example.proskycourse2.examineservice.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService out;


    public static Stream<Arguments> provideParamsForTestQuestionAndAnswer() {
        return Stream.of(
                Arguments.of(new Question("Вопрос 1", "Ответ 1")),
                Arguments.of(new Question("Вопрос 2", "Ответ 2")),
                Arguments.of(new Question("Вопрос 3", "Ответ 3"))
        );
    }

    @BeforeEach
    void setUp() {
        out = new JavaQuestionService();
    }

    @Test
    void addQuestionAndAnswer_ShouldExceptionsWhenNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            out.add(null, null);
        });
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void getQuestionAndAnswer_ShouldReturnCorrectAll(Question question) {
        out.add(question);

        Collection<Question> actual = out.getAll();

        assertThat(actual).hasSize(1)
                .containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void removeQuestionAndAnswerShouldRemoveExistingQuestion(Question question) {
        out.add(question);
        out.remove(question);

        Collection<Question> actual = out.getAll();

        assertThat(actual).doesNotContain(question);
    }

    @Test
    void add_ShouldAddQuestionToCollection() {
        Question question = out.add("Что такое ООП?", "Объектно-ориентированное программирование");

        assertNotNull(question);
        assertEquals(1, out.getAll().size());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestQuestionAndAnswer")
    void addQuestion_ShouldAddQuestionToCollection(Question question) {
        out.add(question);

        assertNotNull(question);
        assertEquals(1, out.getAll().size());
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestion() {
        out.add("Question 1", "Answer 1");
        out.add("Question 2", "Answer 2");

        Question question = out.getRandomQuestion();

        assertNotNull(question);
        assertTrue(out.getAll().contains(question));
    }

    @Test
    void getRandomQuestion_ShouldThrowException_WhenNoQuestionsAvailable() {
        assertThrows(IllegalStateException.class, () -> out.getRandomQuestion());
    }
}


