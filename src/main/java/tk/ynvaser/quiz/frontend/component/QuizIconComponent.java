package tk.ynvaser.quiz.frontend.component;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import tk.ynvaser.quiz.model.quiz.Quiz;

public class QuizIconComponent extends VerticalLayout {
    private final transient Quiz quiz;
    private final Paragraph quizName = new Paragraph();
    private final Icon quizIcon = new Icon(VaadinIcon.QUESTION_CIRCLE_O);

    public QuizIconComponent(Quiz quiz) {
        this.quiz = quiz;
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addIcon();
        addLabel();
    }

    private void addLabel() {
        quizName.setText(quiz.getName());
        quizName.setMaxWidth("90px");
        add(quizName);
    }

    private void addIcon() {
        quizIcon.getStyle().set("cursor", "pointer");
        quizIcon.setSize("90px");
        add(quizIcon);
    }
}
