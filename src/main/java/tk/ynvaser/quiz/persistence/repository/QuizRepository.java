package tk.ynvaser.quiz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, String> {
}
