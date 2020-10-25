package tk.ynvaser.quiz.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.service.QuizService;

@Route(value = "actualQuiz")
public class QuizView extends VerticalLayout {

    private QuizService quizService;

    @Autowired
    public QuizView(QuizService quizService) {
        this.quizService = quizService;
        initVaadinLayout();
    }

    private void initVaadinLayout() {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }

}
