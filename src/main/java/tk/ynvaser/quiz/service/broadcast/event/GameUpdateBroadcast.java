package tk.ynvaser.quiz.service.broadcast.event;

import lombok.Value;
import tk.ynvaser.quiz.model.engine.Game;

@Value
public class GameUpdateBroadcast {
    Game game;
}
