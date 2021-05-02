package tk.ynvaser.quiz.frontend.view.login;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;
import tk.ynvaser.quiz.frontend.view.games.ActiveGamesView;
import tk.ynvaser.quiz.frontend.view.registration.RegistrationView;
import tk.ynvaser.quiz.security.SecurityUtils;

@Route
@PageTitle("Login")
@JsModule("./styles/shared-styles.js")
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
public class LoginView extends LoginOverlay
        implements AfterNavigationObserver, BeforeEnterObserver {

    public LoginView() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Castrum Sylvanum Quiz App");
        i18n.setForm(new LoginI18n.Form());
        i18n.getForm().setSubmit("Bejelentkezés");
        i18n.getForm().setTitle("Bejelentkezés");
        i18n.getForm().setUsername("Felhasználónév");
        i18n.getForm().setPassword("Jelszó");
        i18n.getForm().setForgotPassword("Regisztráció");
        setI18n(i18n);
        setForgotPasswordButtonVisible(true);
        addForgotPasswordListener(e -> UI.getCurrent().navigate(RegistrationView.class));
        setAction("login");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityUtils.isUserLoggedIn()) {
            event.forwardTo(ActiveGamesView.class);
        } else {
            setOpened(true);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        setError(
                event.getLocation().getQueryParameters().getParameters().containsKey(
                        "error"));
    }
}
