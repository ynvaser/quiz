package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Quiz;


public class QuizComponent extends Div {
    Grid<Category> categoryGrid = new Grid<>();

    public QuizComponent(Quiz quiz) {
        categoryGrid.setHeightByRows(true);
        categoryGrid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT, GridVariant.LUMO_COMPACT);
        categoryGrid.addColumn(Category::getName);
        categoryGrid.addComponentColumn(CategoryComponent::new);
        categoryGrid.setItems(quiz.getCategories().values());
        add(categoryGrid);
    }
}
