package tk.ynvaser.quiz.frontend.event;

import com.vaadin.flow.component.ComponentEvent;
import tk.ynvaser.quiz.frontend.view.games.QuestionSelectDialog;

public class QuestionAnsweredEvent extends ComponentEvent<QuestionSelectDialog> {
    public QuestionAnsweredEvent(QuestionSelectDialog source, boolean fromClient) {
        super(source, fromClient);
    }
}
