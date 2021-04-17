package tk.ynvaser.quiz.frontend.view.admin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dnd.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import tk.ynvaser.quiz.model.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamSortingComponent extends VerticalLayout {
    private final Grid<User> userGrid = new Grid<>();
    private final List<Grid<User>> teamGrids = new ArrayList<>();
    private final FormLayout teamContainer = new FormLayout();
    private transient List<User> draggedItems;
    private Grid<User> dragSource;

    public TeamSortingComponent(List<User> users) {
        teamContainer.setResponsiveSteps(new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("25em", 3),
                new FormLayout.ResponsiveStep("25em", 4),
                new FormLayout.ResponsiveStep("25em", 5));
        userGrid.setHeightByRows(true);
        userGrid.setItems(users);
        userGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        userGrid.addDropListener(this::onGridDrop);
        userGrid.addDragStartListener(this::onGridDragStart);
        userGrid.addDragEndListener(this::onGridDragEnd);
        userGrid.setRowsDraggable(true);
        userGrid.addColumn(User::getName);
        Button addTeamButton = new Button("Add Team");
        addTeamButton.addClickListener(this::createNewTeamGrid);
        add(this.userGrid, addTeamButton, teamContainer);
    }

    private void createNewTeamGrid(ClickEvent<Button> buttonClickEvent) {
        Grid<User> teamGrid = new Grid<>();
        teamGrid.setHeightByRows(true);
        teamGrid.setWidth("25em");
        teamGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        teamGrid.addDropListener(this::onGridDrop);
        teamGrid.addDragStartListener(this::onGridDragStart);
        teamGrid.addDragEndListener(this::onGridDragEnd);
        teamGrid.setRowsDraggable(true);
        TextField teamName = new TextField();
        teamName.setValue("Team Name");
        teamGrid.addColumn(User::getName).setHeader(teamName);
        teamGrids.add(teamGrid);
        teamContainer.add(teamGrid);
    }

    private void onGridDrop(GridDropEvent<User> event) {
        Optional<User> target = event.getDropTargetItem();
        if (target.isPresent() && draggedItems.contains(target.get())) {
            return;
        }
        // Remove the items from the source allUsers
        @SuppressWarnings("unchecked")
        ListDataProvider<User> sourceDataProvider = (ListDataProvider<User>) dragSource
                .getDataProvider();
        List<User> sourceItems = new ArrayList<>(
                sourceDataProvider.getItems());
        sourceItems.removeAll(draggedItems);
        dragSource.setItems(sourceItems);
        // Add dragged items to the target Grid
        Grid<User> targetGrid = event.getSource();
        @SuppressWarnings("unchecked")
        ListDataProvider<User> targetDataProvider = (ListDataProvider<User>) targetGrid
                .getDataProvider();
        List<User> targetItems = new ArrayList<>(
                targetDataProvider.getItems());

        int index = target.map(person -> targetItems.indexOf(person)
                + (event.getDropLocation() == GridDropLocation.BELOW ? 1
                : 0))
                .orElse(0);
        targetItems.addAll(index, draggedItems);
        targetGrid.setItems(targetItems);
    }

    private void onGridDragEnd(GridDragEndEvent<User> event) {
        draggedItems = null;
        dragSource = null;
        userGrid.setDropMode(null);
        teamGrids.forEach(grid -> grid.setDropMode(null));
    }

    private void onGridDragStart(GridDragStartEvent<User> event) {
        draggedItems = event.getDraggedItems();
        dragSource = event.getSource();
        userGrid.setDropMode(GridDropMode.ON_TOP_OR_BETWEEN);
        teamGrids.forEach(grid -> grid.setDropMode(GridDropMode.ON_TOP_OR_BETWEEN));
    }
}
