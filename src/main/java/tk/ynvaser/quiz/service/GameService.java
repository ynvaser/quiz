package tk.ynvaser.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.ynvaser.quiz.dto.TeamDTO;
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.model.engine.Team;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.entity.GameEntity;
import tk.ynvaser.quiz.persistence.repository.GameRepository;
import tk.ynvaser.quiz.service.broadcast.BroadcastService;
import tk.ynvaser.quiz.service.broadcast.event.GameUpdateBroadcast;
import tk.ynvaser.quiz.service.broadcast.event.GamesListBroadcast;
import tk.ynvaser.quiz.service.broadcast.event.QuestionSelectBroadcast;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class provides the interface to create an active game and change the state of the current active games.
 */
@Service
public class GameService {
    private final BroadcastService broadcastService;
    private final GameRepository gameRepository;

    @Autowired
    public GameService(BroadcastService broadcastService, GameRepository gameRepository) {
        this.broadcastService = broadcastService;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void createGame(String name, Quiz quiz, List<TeamDTO> teams) {
        List<Team> teamList = teams.stream().map(Team::fromDto).collect(Collectors.toList());
        Game game = new Game(name, quiz, teamList, teamList.get(0));
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGame(game);
        gameRepository.save(gameEntity);
        broadcastService.getGamesListChannel().publish(new GamesListBroadcast(getActiveGames()));
    }

    @Transactional
    private void updateGame(Game game) {
        GameEntity gameEntity = gameRepository.getOne(game.getId());
        gameEntity.setGame(game);
        gameRepository.save(gameEntity);
        broadcastService.getGameUpdateChannel().publish(new GameUpdateBroadcast(game));
    }

    @Transactional
    public List<Game> getActiveGames() {
        return gameRepository.findAll().stream().map(Game::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public void selectQuestion(Question question, Game game) {
        question.setTakenBy(game.getCurrentTeam());
        updateGame(game);
        broadcastService.getQuestionSelectChannel().publish(new QuestionSelectBroadcast(question));
    }
}
