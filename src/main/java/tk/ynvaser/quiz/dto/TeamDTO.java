package tk.ynvaser.quiz.dto;

import lombok.Value;
import tk.ynvaser.quiz.model.users.User;

import java.util.List;

@Value
public class TeamDTO {
    String teamName;
    List<User> teamMembers;
}
