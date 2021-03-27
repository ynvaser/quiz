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
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.model.quiz.Quiz;
import tk.ynvaser.quiz.service.GameService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;

@Route(value = "active-games", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Aktív Játékok")
@CssImport("./styles/views/quizname/quizname-view.css")
public class ActiveGamesView extends Div {
    private final transient GameService gameService;
    private final Set<QuizComponent> displayedQuizzes = new HashSet<>();
    private final FormLayout quizIconContainer = new FormLayout();

    @Autowired
    public ActiveGamesView(GameService gameService) {
        this.gameService = gameService;
        addClassName("quizname-view");
        setSizeFull();
        addQuizIconContainer();
        initReactiveQuizIcons();
    }

    private void addQuizIconContainer() {
        quizIconContainer.setResponsiveSteps(new ResponsiveStep("25em", 1),
                new ResponsiveStep("25em", 2),
                new ResponsiveStep("25em", 3));
        setQuizIcons(gameService.getActiveGames());
        add(quizIconContainer);
    }

    private void initReactiveQuizIcons() {
        //Makes it reactive amon all clients
        gameService.getFlux().subscribe(games ->
                //If active games is active, we need access to the UI to update it
                getUI().ifPresent(ui -> ui.access(() -> setQuizIcons(games))));
    }

    private void setQuizIcons(List<Game> games) {
        quizIconContainer.removeAll();
        for (Game game : games) {
            quizIconContainer.add(createQuizIcon(game.getQuiz()));
        }
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
