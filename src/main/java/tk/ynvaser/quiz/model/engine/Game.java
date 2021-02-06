package tk.ynvaser.quiz.model.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.util.Utilities;

import java.util.Map;

public class Game {
    private static final ObjectMapper OBJECT_MAPPER = Utilities.getObjectMapper();
    private Map<Team, Integer> teamsAndPoints;
    private Quiz quiz;

    @SneakyThrows
    public Game fromJson(String json) {
        return OBJECT_MAPPER.readValue(json, Game.class);
    }

    @SneakyThrows
    public String toJson() {
        return OBJECT_MAPPER.writeValueAsString(this);
    }
}
