package tk.ynvaser.quiz.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import tk.ynvaser.quiz.model.engine.Game;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    List<Game> activeGames = new ArrayList<>();
    UnicastProcessor<List<Game>> publisher;
    Flux<List<Game>> activeGamesFlux;

    public GameService() {
        this.publisher = UnicastProcessor.create();
        this.activeGamesFlux = publisher.publish().autoConnect();
    }

    public void createGame(Game game) {
        activeGames.add(game);
        publisher.onNext(activeGames);
    }

    public Flux<List<Game>> getFlux() {
        return activeGamesFlux;
    }

    public List<Game> getActiveGames() {
        return activeGames;
    }
}
