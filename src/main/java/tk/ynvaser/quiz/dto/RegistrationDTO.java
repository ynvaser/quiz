package tk.ynvaser.quiz.dto;

import lombok.Data;
import tk.ynvaser.quiz.model.users.Role;

@Data
public class RegistrationDTO {
    private String name;
    private String password;
    private String confirmPassword;
    private Role role;
}
