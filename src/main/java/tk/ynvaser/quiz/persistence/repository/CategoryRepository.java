package tk.ynvaser.quiz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.ynvaser.quiz.persistence.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}
