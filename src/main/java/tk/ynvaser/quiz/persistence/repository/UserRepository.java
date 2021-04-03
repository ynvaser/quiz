package tk.ynvaser.quiz.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tk.ynvaser.quiz.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
