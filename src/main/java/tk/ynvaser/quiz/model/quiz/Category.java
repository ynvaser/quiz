package tk.ynvaser.quiz.model.quiz;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Question> questions;

    public Category(String name) {
        this.name = name;
        this.questions = new ArrayList<>(5);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
