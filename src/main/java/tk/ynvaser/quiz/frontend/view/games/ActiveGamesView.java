package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.frontend.event.QuestionAnsweredEvent;
import tk.ynvaser.quiz.frontend.event.QuestionSelectedEvent;
import tk.ynvaser.quiz.frontend.view.MainView;
import tk.ynvaser.quiz.model.engine.Game;
import tk.ynvaser.quiz.service.GameService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Route(value = "active-games", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Aktív Játékok")
@CssImport("./styles/views/quizname/quizname-view.css")
public class ActiveGamesView extends Div {
    private final transient GameService gameService;
    private final Set<GameComponent> games = new HashSet<>();
    private final GameListingComponent gameListingComponent = new GameListingComponent();
    private final QuestionSelectDialog questionSelectDialog = new QuestionSelectDialog();

    @Autowired
    public ActiveGamesView(GameService gameService) {
        this.gameService = gameService;
        addClassName("quizname-view");
        setSizeFull();
        addGamesList();
        refreshIconsOnPush();
        configureDialog();
    }

    private void configureDialog() {
        addListener(QuestionSelectedEvent.class, e -> gameService.selectQuestion(e.getQuestion()));
        gameService.getSelectedQuestionFlux().subscribe(question -> getUI().ifPresent(ui -> ui.access(() -> {
            questionSelectDialog.setQuestion(question);
            questionSelectDialog.open();
            gameService.updateQuestion(question);
        })));
        questionSelectDialog.addListener(QuestionAnsweredEvent.class, e -> e.getSource().close());
        add(questionSelectDialog);
    }

    private void addGamesList() {
        add(gameListingComponent);
        setGames(gameService.getActiveGames());
    }

    private void refreshIconsOnPush() {
        gameService.getActiveGamesFlux().subscribe(activeGames ->
                getUI().ifPresent(ui -> ui.access(() -> setGames(activeGames))));
    }

    private void setGames(List<Game> games) {
        this.games.clear();
        List<GameIconComponent> gameIconComponents = games.stream().map(this::createQuizIcon).collect(Collectors.toList());
        gameListingComponent.setItems(gameIconComponents);
        add(this.games.toArray(GameComponent[]::new));
    }


    private GameIconComponent createQuizIcon(Game game) {
        GameIconComponent gameIcon = new GameIconComponent(game.getName());
        GameComponent linkedGame = createLinkedGame(game);
        gameIcon.addClickListener(
                e -> handleQuizClickEvent(linkedGame));
        return gameIcon;
    }

    private GameComponent createLinkedGame(Game game) {
        GameComponent linkedGame = new GameComponent(game);
        linkedGame.addListener(QuestionSelectedEvent.class, this::fireEvent);
        linkedGame.setVisible(false);
        games.add(linkedGame);
        return linkedGame;
    }

    private void handleQuizClickEvent(GameComponent linkedQuiz) {
        linkedQuiz.setVisible(!linkedQuiz.isVisible());
    }
}

