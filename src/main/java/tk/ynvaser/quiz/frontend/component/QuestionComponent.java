package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
        selectQuestionButton.addClickListener(this::handleClickEvent);
        add(selectQuestionButton);
    }

    private void handleClickEvent(ClickEvent<Button> buttonClickEvent) {
        QuestionSelectDialog dialog = new QuestionSelectDialog(question);
        dialog.addListener(QuestionSelectDialog.QuestionAnsweredEvent.class, this::onQuestionAnswered);
        add(dialog);
        dialog.open();
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

    private void onQuestionAnswered(QuestionSelectDialog.QuestionAnsweredEvent event) {
        revealQuestion();
        revealAnswer();
        remove(selectQuestionButton);
    }
}
