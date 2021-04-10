package tk.ynvaser.quiz.frontend.event;

import com.vaadin.flow.component.ComponentEvent;
import tk.ynvaser.quiz.frontend.view.games.QuizComponent;
import tk.ynvaser.quiz.model.quiz.Question;

public class QuestionSelectedEvent extends ComponentEvent<QuizComponent> {
    private final transient Question question;

    public QuestionSelectedEvent(QuizComponent source, Question question) {
        super(source, true);
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
