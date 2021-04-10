package tk.ynvaser.quiz.model.engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.persistence.entity.GameEntity;
import tk.ynvaser.quiz.util.Utilities;

import java.util.List;

@Data
public class Game {
    private static final ObjectMapper OBJECT_MAPPER = Utilities.getObjectMapper();
    @JsonIgnore
    private long id;
    private String name;
    private Quiz quiz;
    private List<Team> teams;

    public Game() {
    }

    public Game(@JsonProperty("name") String name, @JsonProperty("quiz") Quiz quiz, @JsonProperty("teams") List<Team> teams) {
        this.name = name;
        this.quiz = quiz;
        this.teams = teams;
    }

    void registerTeam(Team team) {
        teams.add(team);
    }

    public static Game fromEntity(GameEntity entity) {
        Game game = entity.getGame();
        game.setId(entity.getId());
        return game;
    }

    @SneakyThrows
    public static Game fromJson(String json) {
        return OBJECT_MAPPER.readValue(json, Game.class);
    }


    @SneakyThrows
    public String toJson() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}
