package tk.ynvaser.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.model.engine.Team;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.entity.GameEntity;
import tk.ynvaser.quiz.persistence.repository.GameRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    UnicastProcessor<List<Game>> gamesListPublisher;
    Flux<List<Game>> gamesListFlux;
    UnicastProcessor<Game> gamePublisher;
    Flux<Game> gameFlux;
    UnicastProcessor<Question> questionPublisher;
    Flux<Question> questionFlux;

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        gamesListPublisher = UnicastProcessor.create();
        gamesListFlux = gamesListPublisher.publish().autoConnect();
        gamePublisher = UnicastProcessor.create();
        gameFlux = gamePublisher.publish().autoConnect();
        questionPublisher = UnicastProcessor.create();
        questionFlux = questionPublisher.publish().autoConnect();
    }

    public Flux<List<Game>> getGamesListFlux() {
        return gamesListFlux;
    }

    public Flux<Game> getGameFlux() {
        return gameFlux;
    }

    public Flux<Question> getQuestionFlux() {
        return questionFlux;
    }

    @Transactional
    public void createGame(String name, Quiz quiz) {
        Team defaultTeam = new Team("DEFAULT_TEAM", null, Collections.emptyList());
        Game game = new Game(name, quiz, List.of(defaultTeam));
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGame(game);
        gameRepository.save(gameEntity);
        gamesListPublisher.onNext(getActiveGames());
    }

    @Transactional
    public void updateGame(Game game) {
        GameEntity gameEntity = gameRepository.getOne(game.getId());
        gameEntity.setGame(game);
        gameRepository.save(gameEntity);
        gamePublisher.onNext(game);
    }

    @Transactional
    public List<Game> getActiveGames() {
        return gameRepository.findAll().stream().map(Game::fromEntity).collect(Collectors.toList());
    }

    public void selectQuestion(Question question) {
        questionPublisher.onNext(question);
    }
}
