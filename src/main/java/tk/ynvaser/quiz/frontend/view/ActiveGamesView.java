package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.frontend.component.QuizComponent;
import tk.ynvaser.quiz.frontend.component.QuizIconComponent;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.service.QuizService;

import java.util.HashSet;
import java.util.Set;

import static com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;

@Route(value = "active-games-view", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Aktív Játékok")
@CssImport("./styles/views/quizname/quizname-view.css")
public class ActiveGamesView extends Div {
    private final transient QuizService quizService;
    private final Set<QuizComponent> displayedQuizzes = new HashSet<>();

    @Autowired
    public ActiveGamesView(QuizService quizService) {
        this.quizService = quizService;
        addClassName("quizname-view");
        setSizeFull();
        addQuizIcons();
    }

    private void addQuizIcons() {
        FormLayout quizIconContainer = new FormLayout();
        quizIconContainer.setResponsiveSteps(new ResponsiveStep("25em", 1),
                new ResponsiveStep("25em", 2),
                new ResponsiveStep("25em", 3));
        for (Quiz quiz : quizService.getQuizes()) {
            QuizIconComponent quizIcon = createQuizIcon(quiz);
            quizIconContainer.add(quizIcon);
        }
        add(quizIconContainer);
    }

    private QuizIconComponent createQuizIcon(Quiz quiz) {
        QuizIconComponent quizIcon = new QuizIconComponent(quiz);
        QuizComponent linkedQuiz = new QuizComponent(quiz);
        quizIcon.addClickListener(
                e -> handleQuizClickEvent(linkedQuiz));
        return quizIcon;
    }

    private void handleQuizClickEvent(QuizComponent linkedQuiz) {
        if (!displayedQuizzes.contains(linkedQuiz)) {
            add(linkedQuiz);
            displayedQuizzes.add(linkedQuiz);
        } else {
            remove(linkedQuiz);
            displayedQuizzes.remove(linkedQuiz);
        }
    }
}
