package tk.ynvaser.quiz.model.quiz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tk.ynvaser.quiz.persistence.entity.CategoryEntity;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Quiz {
    private final String name;
    private final Map<String, Category> categories;

    public static Quiz fromEntity(QuizEntity entity) {
        Map<String, Category> categories = entity
                .getCategories()
                .stream()
                .collect(Collectors.toMap(CategoryEntity::getName, Category::fromEntity));
        return new Quiz(entity.getName(), categories);
    }
}
