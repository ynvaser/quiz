package tk.ynvaser.quiz.model.quiz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.ynvaser.quiz.persistence.entity.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Category {
    private final String name;
    private final List<Question> questions;

    public static Category fromEntity(CategoryEntity entity) {
        List<Question> questions = entity
                .getQuestions()
                .stream()
                .map(Question::fromEntity)
                .collect(Collectors.toList());
        return new Category(entity.getName(), questions);
    }
}
