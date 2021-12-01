package com.openclassroomsproject.paymybuddy.userInterface.transfert;

import com.openclassroomsproject.paymybuddy.backend.model.BuddyTransaction;
import com.openclassroomsproject.paymybuddy.backend.service.IBuddyTransactionService;
import com.openclassroomsproject.paymybuddy.userInterface.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route (value = "transfer", layout = MainLayout.class)
@PageTitle("Transfer page of PayMyBuddy application")
@CssImport(value = "./my-styles/customNumberField.css", themeFor = "vaadin-number-field")
public class TransferView extends VerticalLayout {
    private final IBuddyTransactionService buddyTransactionService;
    Grid<BuddyTransaction> buddyTransactionGrid = new Grid<>(BuddyTransaction.class);
    final String SEND_MONEY_AND_ADD_CONNECTION_TEXT = "Send Money";
    final String SEND_MONEY_AND_ADD_CONNECTION_BUTTON_TEXT = "Add Connexion";
    final String COMBO_BOX_PLACEHOLDER = "Select A Connection";
    final String BUTTON_PAY_TEXT = "Pay";

    public TransferView(IBuddyTransactionService buddyTransactionService) {
        this.buddyTransactionService = buddyTransactionService;
        addClassName("transfer-view");
        setSizeFull();
        configureBuddyTransactionGrid();


        HorizontalLayout sendMoneyAndAddConnexion = new HorizontalLayout();
        stylizeSendMoneyAndAddConnexion(sendMoneyAndAddConnexion);
        Span sendMoneyAndAddConnexionText = new Span(SEND_MONEY_AND_ADD_CONNECTION_TEXT);
        stylizeSendMoneyAndAddConnexionText(sendMoneyAndAddConnexionText);
        Button sendMoneyAndAddConnexionButton = new Button(SEND_MONEY_AND_ADD_CONNECTION_BUTTON_TEXT);
        stylizeSendMoneyAndAddConnexionButton(sendMoneyAndAddConnexionButton);
        sendMoneyAndAddConnexion.add(sendMoneyAndAddConnexionText, sendMoneyAndAddConnexionButton);
        HorizontalLayout selectAConnexionAndPay = new HorizontalLayout();
        stylizeSelectAConnexionAndPay(selectAConnexionAndPay);
        ComboBox<String> selectAConnexionComboBox = new ComboBox<>();
        selectAConnexionComboBox.setPlaceholder(COMBO_BOX_PLACEHOLDER);
        stylizeSelectAConnexionComboBox(selectAConnexionComboBox);
        Button buttonPay = new Button(BUTTON_PAY_TEXT);
        stylizeButtonPay(buttonPay);
        // TODO the custom numberField must be redone and implemented here
        // TODO CustomNumberField customNumberField = new CustomNumberField(0, 1, -1);
        selectAConnexionAndPay.add(selectAConnexionComboBox, buttonPay);
        add(sendMoneyAndAddConnexion, selectAConnexionAndPay, buddyTransactionGrid);
        updateList();
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
                .set("background-color", "#F5F5F5")
                .set("width", "100%")
                .set("justify-content", "center")
                .set("align-items", "center")
                .set("margin-top", "0")
                .set("padding-top", "3%")
                .set("padding-bottom", "3%");
    }

    private void stylizeSelectAConnexionComboBox(ComboBox<String> selectAConnexionComboBox) {
        selectAConnexionComboBox
                .getElement()
                .getStyle()
                .set("height", "100%")
                .set("padding-top", "5%");
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

    public void configureBuddyTransactionGrid() {
        buddyTransactionGrid.setColumns("userAccountEmail", "description", "amount");
    }

    private void updateList() {
        buddyTransactionGrid.setItems(buddyTransactionService.findAllBuddyTransaction());
    }
}