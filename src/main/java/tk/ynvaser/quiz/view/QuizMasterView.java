package tk.ynvaser.quiz.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.service.QuizService;

@Route
public class QuizMasterView extends VerticalLayout {

    private QuizService quizService;

    @Autowired
    public QuizMasterView(QuizService quizService) {
        this.quizService = quizService;
        initVaadinLayout();
    }

    private void initVaadinLayout() {
        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }

}
