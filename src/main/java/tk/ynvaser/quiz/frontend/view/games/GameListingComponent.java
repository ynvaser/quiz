package tk.ynvaser.quiz.frontend.view.games;

import com.vaadin.flow.component.formlayout.FormLayout;

import java.util.List;

public class GameListingComponent extends FormLayout {

    public GameListingComponent() {
        initLayout();
    }

    public void setItems(List<GameIconComponent> icons) {
        removeAll();
        add(icons.toArray(GameIconComponent[]::new));
    }

    private void initLayout() {
        setResponsiveSteps(new ResponsiveStep("25em", 1),
                new ResponsiveStep("25em", 2),
                new ResponsiveStep("25em", 3));
    }
}
