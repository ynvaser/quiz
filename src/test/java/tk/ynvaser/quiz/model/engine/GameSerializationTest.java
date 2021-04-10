package tk.ynvaser.quiz.model.engine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.model.users.Role;
import tk.ynvaser.quiz.model.users.User;
import tk.ynvaser.quiz.persistence.entity.GameEntity;
import tk.ynvaser.quiz.persistence.repository.GameRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GameSerializationTest {
    @Autowired
    GameRepository gameRepository;

    @Test
    void test() {
        Question question = new Question(1, "qname", "qtext", "qanswer");
        Category category = new Category("cname", List.of(question));
        Quiz quiz = new Quiz("qname", List.of(category));
        Team team = new Team("tname", new User("uname", Role.USER), Collections.emptyList());
        Game game = new Game("gname", quiz, List.of(team));
        String toJson = game.toJson();
        Game game1 = Game.fromJson(toJson);

        System.out.println("\n\n\n");
        System.err.println(toJson);
        System.out.println("\n\n\n");

        GameEntity gameEntity = new GameEntity();
        gameEntity.setGame(game);
        GameEntity entity = gameRepository.save(gameEntity);
        Game game2 = Game.fromEntity(entity);
        GameEntity byId = gameRepository.findAll().get(0);

        System.out.println("\n\n\n");
        System.out.println("\n\n\n");

        Game game4 = byId.getGame();
        assertEquals(game, game2);
    }
}
