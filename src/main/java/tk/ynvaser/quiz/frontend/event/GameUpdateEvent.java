package tk.ynvaser.quiz.frontend.event;

import com.vaadin.flow.component.ComponentEvent;
import tk.ynvaser.quiz.frontend.view.games.GameComponent;


public class GameUpdateEvent extends ComponentEvent<GameComponent> {
    public GameUpdateEvent(GameComponent source) {
        super(source, true);
    }
}
