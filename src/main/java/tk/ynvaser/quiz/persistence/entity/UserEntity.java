package tk.ynvaser.quiz.persistence.entity;

import tk.ynvaser.quiz.model.users.Role;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
