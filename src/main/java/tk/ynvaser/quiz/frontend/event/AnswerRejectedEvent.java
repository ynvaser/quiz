package tk.ynvaser.quiz.frontend.event;

import com.vaadin.flow.component.ComponentEvent;
import tk.ynvaser.quiz.frontend.view.games.QuestionSelectDialog;

public class AnswerRejectedEvent extends ComponentEvent<QuestionSelectDialog> {
    public AnswerRejectedEvent(QuestionSelectDialog source, boolean fromClient) {
        super(source, fromClient);
    }
}
