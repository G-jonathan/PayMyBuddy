package com.openclassroomsproject.paymybuddy.userInterface;

import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Main page | Pay My Buddy")
public class HomeView extends VerticalLayout {
    private final IUserAccountService userAccountService;

    public HomeView(IUserAccountService userAccountService) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        this.userAccountService = userAccountService;
        Image image = new Image("img/paymybuddy.png", "payMyBuddy");
        H1 title = new H1("Welcome to your home");
        stylizeTitle(title);
        Span textSpan = new Span("This is your available balance:       " + getUserBalance() + "        EUROS");
        stylizeTextSpan(textSpan);
        add(image, title, textSpan);
    }

    private double getUserBalance() {
       return userAccountService.getAccountBalance();
    }

    private void stylizeTitle(H1 title) {
        title.getElement().getStyle()
                .set("background-color", "limegreen")
                .set("color", "white")
                .set("white-space", "nowrap")
                .set("padding", "1%")
                .set("border-radius", "10px")
                .set("margin-left", "1.5%")
                .set("margin-top", "1%")
                .set("margin-bottom", "0.2%");
    }

    private void stylizeTextSpan(Span span) {
        span.getStyle()
                .set("font-size", "x-large")
                .set("padding-right", "2%")
                .set("padding-left", "2%")
                .set("padding-top", "1%")
                .set("padding-bottom", "0.8%")
                .set("margin-top", "1%")
                .set("margin-left", "1%")
                .set("color", "blue")
                .set("background-color", "#F3F5F7")
                .set("justify-content", "center")
                .set("border", "2px solid black");
    }
}