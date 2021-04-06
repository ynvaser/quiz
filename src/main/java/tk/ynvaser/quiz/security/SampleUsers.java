package tk.ynvaser.quiz.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import tk.ynvaser.quiz.model.users.Role;
import tk.ynvaser.quiz.persistence.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SampleUsers {
    SAMPLE_ADMIN("david", "password", Role.ADMIN),
    SAMPLE_QUIZ_MASTER("quizMaster", "qm1234", Role.GAME_MASTER),
    SAMPLE_TEAM_LEADER("teamLeader", "tl1234", Role.TEAM_LEADER),
    SAMPLE_USER("user", "user1234", Role.USER);

    private final String username;
    private final String password;
    private final Role role;

    SampleUsers(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    UserEntity getUserEntity(PasswordEncoder passwordEncoder) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setRole(role);
        userEntity.setPasswordHash(passwordEncoder.encode(password));
        return userEntity;
    }

    static List<UserEntity> getSampleUsers(PasswordEncoder passwordEncoder) {
        return Stream.of(values()).map(e -> e.getUserEntity(passwordEncoder)).collect(Collectors.toList());
    }
}
