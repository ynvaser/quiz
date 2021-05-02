package tk.ynvaser.quiz.frontend.view.registration;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import tk.ynvaser.quiz.dto.RegistrationDTO;
import tk.ynvaser.quiz.frontend.view.login.LoginView;
import tk.ynvaser.quiz.service.UserService;

@Route(value = "registration")
@PageTitle("Regisztráció")
@JsModule("./styles/shared-styles.js")
public class RegistrationView extends VerticalLayout {
    public static final String USERNAME_PATTERN = "^\\w{3,29}$";
    private final transient UserService userService;
    private final TextField usernameField = new TextField("Username");
    private final PasswordField passwordField = new PasswordField("Password");
    private final PasswordField confirmPasswordField = new PasswordField("Confirm Password");
    private final Button registrationButton = new Button("Regisztrálok");
    private final Div errorContainer = new Div();

    @Autowired
    public RegistrationView(UserService userService) {
        this.userService = userService;
        configureTextFields();
        configureButton();
        super.add(usernameField, passwordField, confirmPasswordField, registrationButton, errorContainer);
    }

    private void configureTextFields() {
        usernameField.setPattern(USERNAME_PATTERN);
        passwordField.setMinLength(4);
        passwordField.setMaxLength(255);
    }


    private void configureButton() {
        registrationButton.addClickListener(e -> registerUser());
    }

    private void registerUser() {
        errorContainer.removeAll();
        if (fieldsAreValid()) {
            RegistrationDTO registrationDTO = getRegistration();
            userService.registerUser(registrationDTO);
            UI.getCurrent().navigate(LoginView.class);
        }
    }

    private RegistrationDTO getRegistration() {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setName(usernameField.getValue());
        registrationDTO.setPassword(passwordField.getValue());
        registrationDTO.setConfirmPassword(confirmPasswordField.getValue());
        return registrationDTO;
    }

    private boolean fieldsAreValid() {
        boolean passwordInvalid = passwordField.isInvalid();
        boolean passwordsSame = passwordField.getValue().equals(confirmPasswordField.getValue());
        if (passwordInvalid) {
            errorContainer.add(new Paragraph("A megadott jelszó nem megfelelő!"));
        }
        if (!passwordsSame) {
            errorContainer.add(new Paragraph("A jelszavak nem egyeznek meg!"));
        }
        return !passwordInvalid && passwordsSame;
    }
}
