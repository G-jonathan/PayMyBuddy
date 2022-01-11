package com.openclassroomsproject.paymybuddy.userInterface.bankAccount;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import com.openclassroomsproject.paymybuddy.backend.service.IBankAccountService;
import com.openclassroomsproject.paymybuddy.userInterface.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "bank", layout = MainLayout.class)
public class BankAccountView extends VerticalLayout {
    private final Grid<BankAccount> bankAccountGrid = new Grid<>(BankAccount.class, false);
    public IBankAccountService bankAccountService;

    public BankAccountView(IBankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
        BankAccountForm bankAccountForm = new BankAccountForm(this, bankAccountService);
        addClassName("bank-account-view");
        Span spanText = new Span("Set up a bank account  ");
        configureTextSpan(spanText);
        Button deleteButton = new Button("Delete bank account", buttonClickEvent -> deleteBankAccount());
        configureDeleteButton(deleteButton);
        configureGrid();
        add(spanText, bankAccountForm, bankAccountGrid, deleteButton);
        setSizeFull();
        updateList();
    }

    public void updateList() {
        bankAccountGrid.setItems(bankAccountService.findBankAccountsByUserAccountEmail());
    }

    private void deleteBankAccount() {
        this.bankAccountService.deleteABankAccount();
        updateList();
    }

    public void configureGrid() {
        Span firstSpan = new Span("firstname");
        configureGridSpan(firstSpan);
        Span secondSpan = new Span("Lastname");
        configureGridSpan(secondSpan);
        Span thirdSpan = new Span("iban");
        configureGridSpan(thirdSpan);
        Span fifthSpan = new Span("bic");
        configureGridSpan(fifthSpan);
        bankAccountGrid
                .getStyle()
                .set("border", "2px solid black")
                .set("align-items", "center");
        bankAccountGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        bankAccountGrid.addColumn(BankAccount::getHolderFirstName).setHeader(firstSpan);
        bankAccountGrid.addColumn(BankAccount::getHolderLastName).setHeader(secondSpan);
        bankAccountGrid.addColumn(BankAccount::getIban).setHeader(thirdSpan);
        bankAccountGrid.addColumn(BankAccount::getBic).setHeader(fifthSpan);
    }

    private void configureGridSpan(Span gridSpan) {
        gridSpan
                .getStyle()
                .set("font-weight", "bold")
                .set("color", "white")
                .set("font-size", "large");
    }

    private void configureTextSpan(Span textSpan) {
        textSpan
                .getStyle()
                .set("font-size", "x-large");
    }

    private void configureDeleteButton(Button deleteButton) {
        deleteButton
                .getElement()
                .getStyle()
                .set("background-color", "#d64f4f") //"#0275D8"
                .set("color", "white")
                .set("border-radius", "10px")
                .set("padding", "1% 3% 2.5% 3%")
                .set("font-weight", "bold");
    }
}