package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.service.QuizService;

@Route(value = "active-games-view", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Aktív Játékok")
@CssImport("./styles/views/quizname/quizname-view.css")
public class ActiveGamesView extends Div {

    @Autowired
    public ActiveGamesView(QuizService quizService) {
        addClassName("quizname-view");
        setSizeFull();
    }
}
