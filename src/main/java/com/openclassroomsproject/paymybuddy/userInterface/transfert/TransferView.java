package com.openclassroomsproject.paymybuddy.userInterface.transfert;

import com.openclassroomsproject.paymybuddy.backend.model.VisibleBuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.service.*;
import com.openclassroomsproject.paymybuddy.userInterface.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route (value = "transfer", layout = MainLayout.class)
@PageTitle("Transfer page of PayMyBuddy application")
@CssImport(value = "./my-styles/customNumberField.css", themeFor = "vaadin-number-field")
@CssImport(value = "./my-styles/customGridStyle.css", themeFor = "vaadin-grid")
@CssImport(value = "./my-styles/customComboBoxStyle.css", themeFor = "vaadin-combo-box")
@CssImport(value = "./my-styles/customTextFieldStyle.css", themeFor = "vaadin-text-field")
public class TransferView extends VerticalLayout {

    private final IBuddyTransactionService buddyTransactionService;
    private final IBankTransactionService bankTransactionService;
    private final IConnexionService connexionService;
    private final IUserAccountService userAccountService;
    private final IBankAccountService bankAccountService;
    Grid<VisibleBuddyTransaction> buddyTransactionGrid = new Grid<>(VisibleBuddyTransaction.class, false);
    ComboBox<String> selectAConnexionComboBox = new ComboBox<>();
    NumberField numberField = new NumberField();
    final String SEND_MONEY_AND_ADD_CONNECTION_TEXT = "Send Money";
    final String MY_TRANSACTION_TEXT = "My Transactions";
    final String SEND_MONEY_AND_ADD_CONNECTION_BUTTON_TEXT = "Add Connexion";
    final String COMBO_BOX_PLACEHOLDER = "Select Connection";
    final String BUTTON_PAY_TEXT = "Pay";

    public TransferView(IBuddyTransactionService buddyTransactionService, IBankTransactionService bankTransactionService, IConnexionService connexionService, IUserAccountService userAccountService, IBankAccountService bankAccountService) {
        this.buddyTransactionService = buddyTransactionService;
        this.bankTransactionService = bankTransactionService;
        this.connexionService = connexionService;
        this.userAccountService = userAccountService;
        this.bankAccountService = bankAccountService;
        addClassName("transfer-view");
        setSizeFull();
        configureBuddyTransactionGrid();
        configureSelectAConnexionComboBox();
        configureAndStylizeIntegerField();
        HorizontalLayout sendMoneyAndAddConnexion = new HorizontalLayout();
        stylizeSendMoneyAndAddConnexion(sendMoneyAndAddConnexion);
        Span sendMoneyAndAddConnexionText = new Span(SEND_MONEY_AND_ADD_CONNECTION_TEXT);
        stylizeSendMoneyAndAddConnexionText(sendMoneyAndAddConnexionText);
        Button sendMoneyAndAddConnexionButton = new Button(SEND_MONEY_AND_ADD_CONNECTION_BUTTON_TEXT, buttonClickEvent -> {
            UI.getCurrent().navigate("contact");
        });
        stylizeSendMoneyAndAddConnexionButton(sendMoneyAndAddConnexionButton);
        sendMoneyAndAddConnexion.add(sendMoneyAndAddConnexionText, sendMoneyAndAddConnexionButton);
        HorizontalLayout selectAConnexionAndPay = new HorizontalLayout();
        stylizeSelectAConnexionAndPay(selectAConnexionAndPay);
        Button buttonPay = new Button(BUTTON_PAY_TEXT, buttonClickEvent -> {
            buttonPayValidation();
        });
        stylizeButtonPay(buttonPay);
        Span myTransactionsSpan = new Span(MY_TRANSACTION_TEXT);
        stylizeMyTransactions(myTransactionsSpan);
        selectAConnexionAndPay.add(selectAConnexionComboBox, numberField, buttonPay);
        add(sendMoneyAndAddConnexion, selectAConnexionAndPay, myTransactionsSpan, buddyTransactionGrid);
        updateTransactionsGrid();
        updateComboBox();
    }

    private void stylizeSendMoneyAndAddConnexion(HorizontalLayout sendMoneyAndAddConnexion) {
        sendMoneyAndAddConnexion
                .getElement()
                .getStyle()
                .set("width", "100%");
    }

    private void stylizeSendMoneyAndAddConnexionText(Span sendMoneyAndAddConnexionText) {
        sendMoneyAndAddConnexionText
                .getElement()
                .getStyle()
                .set("font-size", "x-large")
                .set("align-self", "self-end");
    }

    private void stylizeSendMoneyAndAddConnexionButton(Button sendMoneyAndAddConnexionButton) {
        sendMoneyAndAddConnexionButton
                .getElement()
                .getStyle()
                .set("background-color", "#0275D8")
                .set("color", "white")
                .set("border-radius", "10px")
                .set("padding", "1% 3% 2.5% 3%")
                .set("margin", "0 0 0 auto")
                .set("font-weight", "bold");
    }

    private void stylizeSelectAConnexionAndPay(HorizontalLayout selectAConnexionAndPay) {
        selectAConnexionAndPay
                .getElement()
                .getStyle()
                .set("background-color", "#F3F5F7")
                .set("width", "100%")
                .set("justify-content", "center")
                .set("align-items", "center")
                .set("margin-top", "0")
                .set("padding-top", "3%")
                .set("padding-bottom", "3%");
    }

    private void stylizeButtonPay(Button buttonPay) {
        buttonPay
                .getElement()
                .getStyle()
                .set("background-color", "#5CB85C")
                .set("padding", "1% 6% 2.5% 6%")
                .set("color", "white")
                .set("font-weight", "bold")
                .set("font-size", "large")
                .set("border-radius", "10px");
    }

    private void stylizeMyTransactions(Span myTransactionsSpan) {
        myTransactionsSpan
                .getStyle()
                .set("font-size", "x-large")
                .set("align-self", "start");
    }

    public void configureAndStylizeIntegerField() {
        numberField.setStep(1);
        numberField.setValue(1.0);
        numberField.setHasControls(true);
        numberField.setMin(1);
        numberField.setMax(100);
        numberField
                .getElement()
                .getStyle()
                .set("background-color", "white")
                .set("border", "2px solid black")
                .set("padding-top", "0.5%")
                .set("padding-bottom", "0.5%");
    }

    public void configureBuddyTransactionGrid() {
        Span firstSpan = new Span("Connexions");
        firstSpan
                .getStyle()
                .set("font-weight", "bold")
                .set("color", "white")
                .set("font-size", "large");
        Span secondSpan = new Span("Description");
        secondSpan
                .getStyle()
                .set("font-weight", "bold")
                .set("color", "white")
                .set("font-size", "large");
        Span thirdSpan = new Span("Amount");
        thirdSpan
                .getStyle()
                .set("font-weight", "bold")
                .set("color", "white")
                .set("font-size", "large");
        Span fifthSpan = new Span("Charges");
        fifthSpan
                .getStyle()
                .set("font-weight", "bold")
                .set("color", "white")
                .set("font-size", "large");
        buddyTransactionGrid
                .getStyle()
                .set("border", "2px solid black")
                .set("align-items", "center");
        buddyTransactionGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        buddyTransactionGrid.addColumn(VisibleBuddyTransaction::getConnexionEmail).setHeader(firstSpan);
        buddyTransactionGrid.addColumn(VisibleBuddyTransaction::getDescription).setHeader(secondSpan);
        buddyTransactionGrid.addColumn(VisibleBuddyTransaction::getAmount).setHeader(thirdSpan);
        buddyTransactionGrid.addColumn(VisibleBuddyTransaction::getCharges).setHeader(fifthSpan);
    }

    public void configureSelectAConnexionComboBox() {
        selectAConnexionComboBox.setPlaceholder(COMBO_BOX_PLACEHOLDER);
        selectAConnexionComboBox
                .getElement()
                .getStyle()
                .set("border", "2px solid black")
                .set("padding-top", "0.20%")
                .set("padding-bottom", "0.25%");
    }

    private void updateTransactionsGrid() {
        List<VisibleBuddyTransaction> visibleBuddyTransactionList = buddyTransactionService.findAllUserBuddyTransactions();
        List<VisibleBuddyTransaction> visibleTransactionList = bankTransactionService.findAllBankTransactionUser();
        List<VisibleBuddyTransaction> gridList = new ArrayList<>();
        gridList = Stream.concat(visibleBuddyTransactionList.stream(), visibleTransactionList.stream()).collect(Collectors.toList());
        buddyTransactionGrid.setItems(gridList);
    }

    private void updateComboBox() {
        boolean accountAlreadyRegistered = bankAccountService.checkIfUserHasRegisteredABankAccount();
        if (accountAlreadyRegistered) {
            List<String> comboBoxStringList = new ArrayList<>();
            comboBoxStringList.add("BANK WITHDRAWAL");
            comboBoxStringList.add("BANK DEPOSIT");
            List<String> connexionEmailList = connexionService.findAllConnexionByUserAccountEmail();
            comboBoxStringList.addAll(connexionEmailList);
            selectAConnexionComboBox.setItems(comboBoxStringList);
        } else {
            selectAConnexionComboBox.setItems(connexionService.findAllConnexionByUserAccountEmail());
        }
    }

    private void buttonPayValidation() {
        boolean result;
        VisibleBuddyTransaction transaction = new VisibleBuddyTransaction();
        transaction.setConnexionEmail(selectAConnexionComboBox.getValue());
        transaction.setAmount(numberField.getValue());
        transaction.setDate(LocalDate.now());
        if (selectAConnexionComboBox.getValue().equals("BANK WITHDRAWAL") || selectAConnexionComboBox.getValue().equals("BANK DEPOSIT")) {
             result = bankTransactionService.addBankTransaction(transaction);
        } else {
             result = buddyTransactionService.addBuddyTransaction(transaction);
        }
        if (result) {
            Notification notificationSuccess = Notification.show("Successful transaction !");
            notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else {
            Notification notificationError = Notification.show("Transaction canceled, an error has occurred --> balance insufficient or amount can't be 0");
            notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}