package tk.ynvaser.quiz.model.quiz;

import lombok.*;
import tk.ynvaser.quiz.model.engine.Team;
import tk.ynvaser.quiz.persistence.entity.QuestionEntity;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Question {
    private final int points;
    private final String name;
    private final String text;
    private final String answerText;
    @Setter
    private Team takenBy;

    public static Question fromEntity(QuestionEntity entity) {
        return new Question(entity.getPoints(), entity.getName(), entity.getText(), entity.getAnswerText());
    }
}


