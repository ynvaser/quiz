package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GameIconComponent extends VerticalLayout {
    private final Paragraph quizName = new Paragraph();
    private final Icon quizIcon = new Icon(VaadinIcon.QUESTION_CIRCLE_O);

    public GameIconComponent(String title) {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        addIcon();
        addLabel(title);
    }

    private void addLabel(String title) {
        quizName.setText(title);
        quizName.setMaxWidth("90px");
        add(quizName);
    }

    private void addIcon() {
        quizIcon.getStyle().set("cursor", "pointer");
        quizIcon.setSize("90px");
        add(quizIcon);
    }
}
