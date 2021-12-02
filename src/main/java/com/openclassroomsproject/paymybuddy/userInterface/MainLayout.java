package com.openclassroomsproject.paymybuddy.userInterface;

import com.openclassroomsproject.paymybuddy.configuration.security.SecurityProvider;
import com.openclassroomsproject.paymybuddy.userInterface.transfert.TransferView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.*;

@CssImport("./my-styles/styles.css")
public class MainLayout extends AppLayout {
    private final SecurityProvider securityProvider;

    public MainLayout(SecurityProvider securityProvider) {
        this.securityProvider = securityProvider;
        createHeader();
    }

    private void createHeader() {
        H1 title = new H1("Pay My Buddy");
        stylizeTitle(title);
        Tab homeLink = new Tab(new RouterLink("Home", HomeView.class));
        stylizeLink(homeLink);
        Tab transferLink = new Tab(new RouterLink("Transfer", TransferView.class));
        stylizeLink(transferLink);
        Tab profileLink = new Tab(new RouterLink("Profile", ProfileView.class));
        stylizeLink(profileLink);
        Tab contactLink = new Tab(new RouterLink("Contact", ContactView.class));
        stylizeLink(contactLink);
        Tab logOffLink = new Tab(new Button("Log out", e -> securityProvider.logout()));
        stylizeLink(logOffLink);
        HorizontalLayout linkLayout = new HorizontalLayout(homeLink, transferLink, profileLink, contactLink, logOffLink);
        stylizeLinkLayout(linkLayout);
        HorizontalLayout titleAndLinksLayout = new HorizontalLayout(title, linkLayout);
        stylizeTitleAndLinks(titleAndLinksLayout);
        Span pathLayout = new Span("Home /");
        stylizePath(pathLayout);
        VerticalLayout header = new VerticalLayout(titleAndLinksLayout, pathLayout);
        stylizeHeader(header);
        addToNavbar(header);
    }

    private void stylizeTitle(H1 title) {
        title.getElement().getStyle()
                .set("background-color", "limegreen")
                .set("color", "white")
                .set("white-space", "nowrap")
                .set("padding", "1%")
                .set("border-radius", "10px")
                .set("margin-left", "1.5%")
                .set("margin-top", "0.2%")
                .set("margin-bottom", "0.2%");
    }

    private void stylizeLink(Tab tab) {
        tab.addClassName("customLink");
        tab.getElement().getStyle()
                .set("font-size", "x-large");
    }

    private void stylizePath(Span path) {
        path.getElement().getStyle()
                .set("width", "100%")
                .set("font-size", "x-large")
                .set("padding-left", "2%")
                .set("padding-top", "1%")
                .set("padding-bottom", "0.8%")
                .set("margin-top", "0")
                .set("color", "blue")
                .set("background-color", "lightgray");
    }

    private void stylizeLinkLayout(HorizontalLayout linkLayout) {
        linkLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        linkLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        linkLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        linkLayout.setWidth("100%");
        linkLayout.getElement().getStyle()
                .set("margin-right", "2%")
                .set("margin-top", "0.2%")
                .set("margin-bottom", "0.2%");
    }

    private void stylizeTitleAndLinks(HorizontalLayout titleAndLinks) {
        titleAndLinks.setWidth("100%");
        titleAndLinks.getElement().getStyle()
                .set("background-color", "white");
    }

    private void stylizeHeader(VerticalLayout header) {
        header.setPadding(false);
        header.setMargin(false);
    }
}