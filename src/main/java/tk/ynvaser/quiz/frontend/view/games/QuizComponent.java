package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import tk.ynvaser.quiz.frontend.event.QuestionSelectedEvent;
import tk.ynvaser.quiz.model.quiz.Category;
import tk.ynvaser.quiz.model.quiz.Question;
import tk.ynvaser.quiz.model.quiz.Quiz;

public class QuizComponent extends Div {
    Grid<Category> categoryGrid = new Grid<>();

    public QuizComponent() {
        categoryGrid.setHeightByRows(true);
        categoryGrid.addColumn(Category::getName)
                .setHeader("Category")
                .setAutoWidth(true)
                .setResizable(true);
        categoryGrid.addComponentColumn(this::createQuestionGrid)
                .setHeader("Questions")
                .setAutoWidth(true)
                .setResizable(true);
        categoryGrid.setRowsDraggable(true);
        categoryGrid.setVerticalScrollingEnabled(true);
        add(categoryGrid);
    }

    private Grid<Question> createQuestionGrid(Category category) {
        Grid<Question> questionGrid = new Grid<>();
        questionGrid.setHeightByRows(true);
        questionGrid.addColumn(Question::getName).setHeader("Name")
                .setAutoWidth(true)
                .setResizable(true);
        questionGrid.addColumn(Question::getPoints).setHeader("Point Value")
                .setAutoWidth(true)
                .setResizable(true);
        questionGrid.addColumn(this::getTakenBy).setHeader("Taken By")
                .setAutoWidth(true)
                .setResizable(true);
        questionGrid.addItemClickListener(this::onComponentEvent);
        questionGrid.setItems(category.getQuestions());
        return questionGrid;
    }

    private String getTakenBy(Question question) {
        return question.getTakenBy() == null ? "" : question.getTakenBy().getName();
    }

    private void onComponentEvent(ItemClickEvent<Question> e) {
        fireEvent(new QuestionSelectedEvent(this, e.getItem()));
    }

    public void setQuiz(Quiz quiz) {
        categoryGrid.setItems(quiz.getCategories());
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
