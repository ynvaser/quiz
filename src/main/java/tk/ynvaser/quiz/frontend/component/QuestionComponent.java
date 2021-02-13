package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import tk.ynvaser.quiz.model.quiz.Question;


public class QuestionComponent extends HorizontalLayout {
    private final transient Question question;

    private final Paragraph name = new Paragraph();
    private final Paragraph pointValue = new Paragraph();
    private final Paragraph questionText = new Paragraph();
    private final Paragraph answerText = new Paragraph();
    private final Button selectQuestionButton = new Button("Select");

    public QuestionComponent(Question question) {
        this.question = question;

        addName(question);
        addPointValue(question);
        addQuestion(question);
        addAnswer(question);
        addButton();
    }

    private void addName(Question question) {
        name.setText(question.getName());
        add(name);
    }

    private void addPointValue(Question question) {
        pointValue.setText(Integer.toString(question.getPoints()));
        add(pointValue);
    }

    private void addQuestion(Question question) {
        questionText.setText(question.getText());
        questionText.setVisible(false);
        add(questionText);
    }

    private void addAnswer(Question question) {
        answerText.setText(question.getAnswerText());
        answerText.setVisible(false);
        add(answerText);
    }

    private void addButton() {
        selectQuestionButton.setEnabled(true);
        add(selectQuestionButton);
    }

    public void revealQuestion() {
        questionText.setVisible(true);
    }

    public void revealAnswer() {
        answerText.setVisible(true);
    }

    public void enableSelect() {
        selectQuestionButton.setEnabled(true);
    }

    public void disableSelect() {
        selectQuestionButton.setEnabled(false);
    }

    public static class SelectQuestionEvent extends ComponentEvent<QuestionComponent> {
        private final transient Question question;

        public SelectQuestionEvent(QuestionComponent source, boolean fromClient) {
            super(source, fromClient);
            this.question = source.question;
        }

        public Question getQuestion() {
            return question;
        }
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
