package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;

public class CategoryComponent extends HorizontalLayout {

    public CategoryComponent(Category category) {
        Grid<Question> questionGrid = new Grid<>();
        questionGrid.setHeightByRows(true);
        questionGrid.addComponentColumn(QuestionComponent::new);
        questionGrid.setItems(category.getQuestions());
        add(questionGrid);
    }
}
