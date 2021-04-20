package tk.ynvaser.quiz.service.broadcast;

import lombok.Value;
import org.springframework.stereotype.Service;
import tk.ynvaser.quiz.service.broadcast.event.GameUpdateBroadcast;
import tk.ynvaser.quiz.service.broadcast.event.GamesListBroadcast;
import tk.ynvaser.quiz.service.broadcast.event.QuestionSelectBroadcast;

/**
 * This service's job is to notify all clients of server based events.
 */
@Service
@Value
public class BroadcastService {
    BroadcastChannel<GameUpdateBroadcast> gameUpdateChannel = new BroadcastChannel<>();
    BroadcastChannel<GamesListBroadcast> gamesListChannel = new BroadcastChannel<>();
    BroadcastChannel<QuestionSelectBroadcast> questionSelectChannel = new BroadcastChannel<>();
}
