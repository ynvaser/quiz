package tk.ynvaser.quiz.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tk.ynvaser.quiz.persistence.entity.UserEntity;

@Data
public class User {
    @JsonIgnore
    private long id;
    private String name;
    private Role role;

    public User(@JsonProperty("name") String name, @JsonProperty("role") Role role) {
        this.name = name;
        this.role = role;
    }

    public static User fromEntity(UserEntity entity) {
        User user = new User(entity.getUsername(), entity.getRole());
        user.setId(entity.getId());
        return user;
    }
}
