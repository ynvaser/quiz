package tk.ynvaser.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.entity.GameEntity;
import tk.ynvaser.quiz.persistence.repository.GameRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    UnicastProcessor<List<Game>> activeGamesPublisher;
    Flux<List<Game>> activeGamesFlux;
    UnicastProcessor<Game> gameStatePublisher;
    Flux<Game> gameStateFlux;
    UnicastProcessor<Question> questionSelectPublisher;
    Flux<Question> questionSelectFlux;

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        activeGamesPublisher = UnicastProcessor.create();
        activeGamesFlux = activeGamesPublisher.publish().autoConnect();

        questionSelectPublisher = UnicastProcessor.create();
        questionSelectFlux = questionSelectPublisher.publish().autoConnect();
    }

    public void createGame(String name, Quiz quiz) {
        Game game = new Game(name, quiz, Collections.emptyList());
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGame(game);
        gameRepository.save(gameEntity);
        List<Game> games = gameRepository.findAll().stream().map(Game::fromEntity).collect(Collectors.toList());
        activeGamesPublisher.onNext(games);
    }

    public void updateQuestion(Question question) {
        findQuestionInGames(question);
    }

    private Game findQuestionInGames(Question question) {
        for (Game activeGame : gameRepository.findAll().stream().map(Game::fromEntity).collect(Collectors.toList())) {
            for (Category category : activeGame.getQuiz().getCategories()) {
                for (Question categoryQuestion : category.getQuestions()) {
                    if (categoryQuestion.equals(question))
                        return activeGame;
                }
            }
        }
        return null;
    }

    public void selectQuestion(Question question) {
        questionSelectPublisher.onNext(question);
    }

    public Flux<Question> getSelectedQuestionFlux() {
        return questionSelectFlux;
    }

    public Flux<List<Game>> getActiveGamesFlux() {
        return activeGamesFlux;
    }

    @Transactional
    public List<Game> getActiveGames() {
        List<GameEntity> gameEntities = gameRepository.findAll();
        List<Game> games = new ArrayList<>();
        for (GameEntity gameEntity : gameEntities) {
            games.add(Game.fromEntity(gameEntity));
        }
        return games;
    }
}
