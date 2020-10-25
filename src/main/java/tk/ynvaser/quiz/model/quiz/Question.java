package tk.ynvaser.quiz.model.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    private int points;
    private String name;
    private String text;
    private String answerText;
}
