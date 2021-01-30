package tk.ynvaser.quiz.model.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.ynvaser.quiz.model.engine.Team;

@Getter
@NoArgsConstructor
public class Question {
    private int points;
    private String name;
    private String text;
    private String answerText;
    @Setter
    private Team takenBy;

    @Builder
    public static Question createQuestion(int points, String name, String text, String answerText) {
        Question question = new Question();
        question.points = points;
        question.name = name;
        question.text = text;
        question.answerText = answerText;
        return question;
    }
}
