package tk.ynvaser.quiz.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import tk.ynvaser.quiz.persistence.entity.QuizEntity;

public interface QuizRepository extends CrudRepository<QuizEntity, String> {
}
