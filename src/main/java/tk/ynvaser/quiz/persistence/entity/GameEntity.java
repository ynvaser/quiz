package tk.ynvaser.quiz.persistence.entity;

import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.persistence.converter.JsonToGameConverter;

import javax.persistence.*;

@Entity
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "JSON")
    @Convert(converter = JsonToGameConverter.class)
    private Game game;

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
