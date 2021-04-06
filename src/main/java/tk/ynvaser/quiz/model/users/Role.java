package tk.ynvaser.quiz.model.users;

import java.util.stream.Stream;

public enum Role {
    ADMIN,
    GAME_MASTER,
    TEAM_LEADER,
    USER;

    public static String[] getRoles() {
        return Stream.of(values()).map(Enum::name).toArray(String[]::new);
    }
}
