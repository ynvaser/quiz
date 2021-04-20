package tk.ynvaser.quiz.frontend.event;

import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;
import tk.ynvaser.quiz.frontend.view.games.QuizComponent;
import tk.ynvaser.quiz.model.quiz.Question;

@Getter
public class QuestionSelectedEvent extends ComponentEvent<QuizComponent> {
    private final transient Question question;

    public QuestionSelectedEvent(QuizComponent source, Question question) {
        super(source, true);
        this.question = question;
    }
}
