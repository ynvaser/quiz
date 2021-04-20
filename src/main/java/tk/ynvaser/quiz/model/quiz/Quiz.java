package tk.ynvaser.quiz.model.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;

import java.util.List;
import java.util.stream.Collectors;

@Value
@ToString
public class Quiz {
    String name;
    List<Category> categories;

    public Quiz(@JsonProperty("name") String name, @JsonProperty("categories") List<Category> categories) {
        this.name = name;
        this.categories = categories;
    }

    public static Quiz fromEntity(QuizEntity entity) {
        List<Category> categories = entity.getCategories().stream().map(Category::fromEntity).collect(Collectors.toList());
        return new Quiz(entity.getName(), categories);
    }
}
