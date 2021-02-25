package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;
import tk.ynvaser.quiz.model.quiz.Question;

public class QuestionSelectDialog extends Dialog {
    private final transient Question question;

    private final Button acceptAnswer = new Button("Accept");
    private final Button rejectAnswer = new Button("Reject");

    public QuestionSelectDialog(Question question) {
        this.question = question;
        configureDialog();
        add(createDialogLayout());
        add(createButtonLayout());
    }

    private void configureDialog() {
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);
        setModal(true);
        configureButtons();
    }

    private void configureButtons() {
        acceptAnswer.addClickListener(event -> {
            fireEvent(new QuestionAnsweredEvent(this, true));
            fireEvent(new AnswerAcceptedEvent(this, true));
            close();
        });
        rejectAnswer.addClickListener(event -> {
            fireEvent(new QuestionAnsweredEvent(this, true));
            fireEvent(new AnswerRejectedEvent(this, true));
            close();
        });
    }

    VerticalLayout createDialogLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new Paragraph(question.getName()));
        layout.add(new Paragraph(question.getText()));
        layout.add(new Paragraph("Points : " + question.getPoints()));
        layout.add(new Paragraph(question.getAnswerText()));
        layout.add(createButtonLayout());
        return layout;
    }

    HorizontalLayout createButtonLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(acceptAnswer);
        layout.add(rejectAnswer);
        return layout;
    }

    public Question getQuestion() {
        return question;
    }

    public Button getAcceptAnswer() {
        return acceptAnswer;
    }

    public Button getRejectAnswer() {
        return rejectAnswer;
    }

    public static class QuestionAnsweredEvent extends ComponentEvent<QuestionSelectDialog> {
        QuestionAnsweredEvent(QuestionSelectDialog source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public static class AnswerAcceptedEvent extends ComponentEvent<QuestionSelectDialog> {
        AnswerAcceptedEvent(QuestionSelectDialog source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    public static class AnswerRejectedEvent extends ComponentEvent<QuestionSelectDialog> {
        AnswerRejectedEvent(QuestionSelectDialog source, boolean fromClient) {
            super(source, fromClient);
        }
    }


    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
