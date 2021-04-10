package tk.ynvaser.quiz.model.engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tk.ynvaser.quiz.model.users.User;

import java.util.List;

@Data
public class Team {
    private String name;
    private User leader;
    private List<User> members;

    public Team(@JsonProperty("name") String name, @JsonProperty("leader") User leader, @JsonProperty("members") List<User> members) {
        this.name = name;
        this.leader = leader;
        this.members = members;
    }
}
