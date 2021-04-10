package tk.ynvaser.quiz.model.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tk.ynvaser.quiz.model.engine.Team;
import tk.ynvaser.quiz.persistence.entity.QuestionEntity;

@Getter
@EqualsAndHashCode
@ToString
public class Question {
    private final int points;
    private final String name;
    private final String text;
    private final String answerText;
    @Setter
    private Team takenBy;

    public Question(@JsonProperty("points") int points, @JsonProperty("name") String name, @JsonProperty("text") String text, @JsonProperty("answerText") String answerText) {
        this.points = points;
        this.name = name;
        this.text = text;
        this.answerText = answerText;
    }

    public static Question fromEntity(QuestionEntity entity) {
        return new Question(entity.getPoints(), entity.getName(), entity.getText(), entity.getAnswerText());
    }
}

