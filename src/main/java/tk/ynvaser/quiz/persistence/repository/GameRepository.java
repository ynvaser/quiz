package tk.ynvaser.quiz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ynvaser.quiz.persistence.entity.GameEntity;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
