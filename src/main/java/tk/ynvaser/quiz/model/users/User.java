package tk.ynvaser.quiz.model.users;

import lombok.Data;

@Data
public abstract class User {
    private final String name;

    protected User(String name) {
        this.name = name;
    }
}
