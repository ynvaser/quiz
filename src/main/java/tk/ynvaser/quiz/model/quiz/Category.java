package tk.ynvaser.quiz.model.quiz;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Category {
    private final String name;
    private List<Question> questions = new ArrayList<>(5);

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
