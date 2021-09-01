package com.openclassroomsproject.paymybuddy.userInterface;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route (value = "transfer", layout = MainLayout.class)
public class TransferView extends VerticalLayout {

    private Grid<UserAccount> grid = new Grid<>(UserAccount.class);
    private IUserAccountService userAccountService;

    public TransferView(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
        addClassName("list-view");
        setSizeFull();
        add(grid);
        updateList();
    }

    private void updateList() {
        grid.setItems(userAccountService.findAllUserAccount());
    }
}