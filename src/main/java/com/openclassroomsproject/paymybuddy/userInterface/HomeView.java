package com.openclassroomsproject.paymybuddy.userInterface;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Main page | Pay My Buddy")
public class HomeView extends VerticalLayout {

    public HomeView() {
    }
}