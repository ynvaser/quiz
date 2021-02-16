package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Quiz;


public class QuizComponent extends Div {
    private final transient Quiz quiz;

    public QuizComponent(Quiz quiz) {
        this.quiz = quiz;
        Grid<Category> categoryGrid = new Grid<>();
        categoryGrid.setHeightByRows(true);
        categoryGrid.addColumn(Category::getName);
        categoryGrid.addComponentColumn(CategoryComponent::new);
        categoryGrid.setItems(quiz.getCategories().values());
        add(categoryGrid);
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
