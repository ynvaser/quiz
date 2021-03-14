package tk.ynvaser.quiz.model.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.util.Utilities;

import java.util.Map;

@Data
public class Game {
    private static final ObjectMapper OBJECT_MAPPER = Utilities.getObjectMapper();
    private final Quiz quiz;
    private Map<Team, Integer> teamsAndPoints;

    void registerTeam(Team team) {
        teamsAndPoints.put(team, 0);
    }

    @SneakyThrows
    public Game fromJson(String json) {
        return OBJECT_MAPPER.readValue(json, Game.class);
    }

    @SneakyThrows
    public String toJson() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}
