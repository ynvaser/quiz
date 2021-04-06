package tk.ynvaser.quiz.persistence.entity;

import lombok.Data;
import tk.ynvaser.quiz.model.users.Role;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\w{3,29}$")
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 4, max = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;
}
