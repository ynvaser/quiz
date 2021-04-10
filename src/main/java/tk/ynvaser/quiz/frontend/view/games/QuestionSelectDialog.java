package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;
import tk.ynvaser.quiz.frontend.event.AnswerAcceptedEvent;
import tk.ynvaser.quiz.frontend.event.AnswerRejectedEvent;
import tk.ynvaser.quiz.frontend.event.QuestionAnsweredEvent;
import tk.ynvaser.quiz.model.quiz.Question;

public class QuestionSelectDialog extends Dialog {
    private transient Question question;

    private final Button acceptAnswer = new Button("Accept");
    private final Button rejectAnswer = new Button("Reject");

    public QuestionSelectDialog() {
        configureDialog();
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

    public void setQuestion(Question question) {
        removeAll();
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new Paragraph(question.getName()));
        layout.add(new Paragraph(question.getText()));
        layout.add(new Paragraph("Points : " + question.getPoints()));
        layout.add(new Paragraph(question.getAnswerText()));
        layout.add(createButtonLayout());
        add(layout);
        add(createButtonLayout());
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


    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
