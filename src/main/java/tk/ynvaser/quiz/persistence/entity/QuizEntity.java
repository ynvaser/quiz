package tk.ynvaser.quiz.persistence.entity;

import tk.ynvaser.quiz.model.quiz.Quiz;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class QuizEntity {
    @Id
    private String name;
    @OneToMany
    private List<CategoryEntity> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public static QuizEntity fromQuiz(Quiz quiz) {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setName(quiz.getName());
        quiz.getCategories().values().stream().map(category -> QuestionEntity.fromCategory(category)).collect(Collectors.toList());
        quizEntity.setCategories(quiz.getCategories().values().stream().map());
    }
}
