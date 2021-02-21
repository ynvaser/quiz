package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Quiz;


public class QuizComponent extends Div {
    private final transient Quiz quiz;

    public QuizComponent(Quiz quiz) {
        this.quiz = quiz;
        addCategoryGrid(quiz);
    }

    private void addCategoryGrid(Quiz quiz) {
        Grid<Category> categoryGrid = new Grid<>();
        categoryGrid.setHeightByRows(true);
        configureNameColumn(categoryGrid);
        configureQuestionColumn(categoryGrid);
        categoryGrid.setItems(quiz.getCategories().values());
        add(categoryGrid);
    }

    private void configureNameColumn(Grid<Category> categoryGrid) {
        Grid.Column<Category> nameColumn = categoryGrid.addColumn(Category::getName);
        nameColumn.setHeader("Category Name");
        nameColumn.setResizable(true);
        nameColumn.setWidth("60px");
    }

    private void configureQuestionColumn(Grid<Category> categoryGrid) {
        Grid.Column<Category> questionColumn = categoryGrid.addComponentColumn(CategoryComponent::new);
        questionColumn.setHeader("Questions");
        questionColumn.setTextAlign(ColumnTextAlign.CENTER);
        questionColumn.setResizable(true);
        questionColumn.setAutoWidth(false);
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
