package tk.ynvaser.quiz.frontend.view.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import tk.ynvaser.quiz.frontend.util.GridUtilities;
import tk.ynvaser.quiz.model.users.User;

import java.util.List;


public class TeamCreatorComponent extends VerticalLayout {
    private final TextField nameField = new TextField();
    private final Grid<User> memberGrid = new Grid<>();
    private final Button deleteButton = new Button("Delete");

    public TeamCreatorComponent(TeamSortingComponent parent) {
        memberGrid.setHeightByRows(true);
        memberGrid.setWidthFull();
        memberGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        memberGrid.addDropListener(parent::onGridDrop);
        memberGrid.addDragStartListener(parent::onGridDragStart);
        memberGrid.addDragEndListener(parent::onGridDragEnd);
        memberGrid.setRowsDraggable(true);
        memberGrid.addColumn(User::getName).setHeader("Members");
        add(nameField, memberGrid, deleteButton);
    }

    public TextField getNameField() {
        return nameField;
    }

    public Grid<User> getMemberGrid() {
        return memberGrid;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public List<User> getTeamMembers() {
        return GridUtilities.getItems(memberGrid);
    }
}
