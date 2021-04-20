package tk.ynvaser.quiz.model.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tk.ynvaser.quiz.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Value
@ToString
public class Category {
    String name;
    List<Question> questions;

    public Category(@JsonProperty("name") String name, @JsonProperty("questions") List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public static Category fromEntity(CategoryEntity entity) {
        List<Question> questions = entity
                .getQuestions()
                .stream()
                .map(Question::fromEntity)
                .collect(Collectors.toList());
        return new Category(entity.getName(), questions);
    }
}
