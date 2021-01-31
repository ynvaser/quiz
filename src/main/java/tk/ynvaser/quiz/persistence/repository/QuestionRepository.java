package tk.ynvaser.quiz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ynvaser.quiz.persistence.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
