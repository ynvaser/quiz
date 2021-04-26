package tk.ynvaser.quiz.service.broadcast.event;

import lombok.Value;
import tk.ynvaser.quiz.model.engine.Game;

import java.util.List;

@Value
public class GamesListBroadcast {
    List<Game> gamesList;
}
