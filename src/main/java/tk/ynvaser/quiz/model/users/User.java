package tk.ynvaser.quiz.model.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tk.ynvaser.quiz.persistence.entity.UserEntity;

@Data
public class User {
    private final String name;
    private final Role role;

    public User(@JsonProperty("name") String name, @JsonProperty("role") Role role) {
        this.name = name;
        this.role = role;
    }

    public static User fromEntity(UserEntity entity) {
        return new User(entity.getUsername(), entity.getRole());
    }
}
