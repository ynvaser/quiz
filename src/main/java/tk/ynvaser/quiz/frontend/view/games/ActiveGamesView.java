package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.Component;
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
import tk.ynvaser.quiz.model.quiz.Question;
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
    private final Set<GameComponent> gameComponents = new HashSet<>();
    private final GameListingComponent gameListingComponent = new GameListingComponent();
    private final QuestionSelectDialog questionSelectDialog = new QuestionSelectDialog();

    @Autowired
    public ActiveGamesView(GameService gameService) {
        this.gameService = gameService;
        addClassName("quizname-view");
        setSizeFull();
        addGamesList();
        configureDialog();
        subscribeToQuestions();
        subscribeToGamesListUpdates();
        subscribeToGameUpdates();
    }

    private void subscribeToQuestions() {
        gameService.getQuestionFlux().subscribe(question -> getUI().ifPresent(ui -> ui.access(() -> showDialog(question))));
    }

    private void addGamesList() {
        add(gameListingComponent);
        setGameComponents(gameService.getActiveGames());
    }

    private void configureDialog() {
        questionSelectDialog.addListener(QuestionAnsweredEvent.class, e -> questionSelectDialog.close());
    }

    private void subscribeToGamesListUpdates() {
        gameService.getGamesListFlux().subscribe(games ->
                getUI().ifPresent(ui -> ui.access(() -> setGameComponents(games))));
    }

    private void subscribeToGameUpdates() {
        gameService.getGameFlux().subscribe(game -> getUI().ifPresent(ui -> ui.access(() -> updateGame(game))));
    }

    private void setGameComponents(List<Game> gameComponents) {
        Set<Long> wereOpen = this.gameComponents.stream().filter(Component::isVisible).map(GameComponent::getGameId).collect(Collectors.toSet());
        remove(this.gameComponents.toArray(GameComponent[]::new));
        this.gameComponents.clear();
        List<GameIconComponent> gameIconComponents = gameComponents.stream().map(this::createQuizIcon).collect(Collectors.toList());
        gameListingComponent.setItems(gameIconComponents);
        add(this.gameComponents.toArray(GameComponent[]::new));
        this.gameComponents.stream().filter(gc -> wereOpen.contains(gc.getGameId())).forEach(gc -> gc.setVisible(true));
    }

    private void updateGame(Game game) {
        for (GameComponent gameComponent : gameComponents) {
            if (gameComponent.getGameId() == game.getId()) {
                gameComponent.setGame(game);
                return;
            }
        }
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
        linkedGame.addListener(QuestionSelectedEvent.class, e -> handleQuestionSelect(e, linkedGame));
        linkedGame.setVisible(false);
        gameComponents.add(linkedGame);
        return linkedGame;
    }

    private void handleQuestionSelect(QuestionSelectedEvent event, GameComponent gameComponent) {
        //Just for testing if the Game Component updates taken by properly
        event.getQuestion().setTakenBy(gameComponent.getGame().getTeams().get(0));
        gameService.updateGame(gameComponent.getGame());
        gameService.selectQuestion(event.getQuestion());
    }

    private void showDialog(Question question) {
        questionSelectDialog.setQuestion(question);
        questionSelectDialog.open();
    }

    private void handleQuizClickEvent(GameComponent linkedQuiz) {
        linkedQuiz.setVisible(!linkedQuiz.isVisible());
    }
}

