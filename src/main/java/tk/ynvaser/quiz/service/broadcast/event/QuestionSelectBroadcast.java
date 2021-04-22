package tk.ynvaser.quiz.service.broadcast.event;

import lombok.Value;
import tk.ynvaser.quiz.model.quiz.Question;

@Value
public class QuestionSelectBroadcast{
    Question question;
}
