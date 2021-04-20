package tk.ynvaser.quiz.frontend.view.usermanagement;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.frontend.view.MainView;
import tk.ynvaser.quiz.model.users.Role;
import tk.ynvaser.quiz.model.users.User;
import tk.ynvaser.quiz.service.UserService;

@Route(value = "user-management-view", layout = MainView.class)
@PageTitle("User Management")
@CssImport("./styles/views/quizname/quizname-view.css")
public class UserManagementView extends VerticalLayout {
    private final transient UserService userService;

    @Autowired
    public UserManagementView(UserService userService) {
        this.userService = userService;
        Grid<User> userGrid = new Grid<>();
        userGrid.setHeightByRows(true);
        userGrid.addColumn(User::getName);
        userGrid.addComponentColumn(this::createRoleSelectForUser);
        userGrid.setItems(userService.getAllUsers());
        add(userGrid);
    }

    private Select<Role> createRoleSelectForUser(User user) {
        Select<Role> select = new Select<>();
        select.setItems(Role.values());
        select.setValue(user.getRole());
        select.addValueChangeListener(event -> userService.setRoleForUser(user, event.getValue()));
        return select;
    }
}
