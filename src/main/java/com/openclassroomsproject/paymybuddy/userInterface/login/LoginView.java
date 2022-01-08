package com.openclassroomsproject.paymybuddy.userInterface.login;

import com.openclassroomsproject.paymybuddy.userInterface.registration.RegistrationForm;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.*;

@PageTitle("Login")
@Route(value = LoginView.ROUTE)
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    public static final String ROUTE = "login";
    private final LoginForm loginForm = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        loginForm.setAction("login");
        Tab registrationLink = new Tab(new RouterLink("Create an account ", RegistrationForm.class));
        H1 title = new H1("PAY MY BUDDY");
        title.getElement().getStyle()
                .set("background-color", "#5CB85C")
                .set("padding", "0.5%")
                .set("border-radius", "10px")
                .set("color", "white");
        add(title, loginForm, registrationLink);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}