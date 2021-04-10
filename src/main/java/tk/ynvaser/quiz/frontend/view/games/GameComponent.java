package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import tk.ynvaser.quiz.frontend.event.QuestionSelectedEvent;
import tk.ynvaser.quiz.model.engine.Game;


public class GameComponent extends Div {
    private final QuizComponent quizComponent = new QuizComponent();

    public GameComponent(Game game) {
        quizComponent.setQuiz(game.getQuiz());
        quizComponent.addListener(QuestionSelectedEvent.class, this::fireEvent);
        add(quizComponent);
    }

    public void setGame(Game game) {
        quizComponent.setQuiz(game.getQuiz());
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
