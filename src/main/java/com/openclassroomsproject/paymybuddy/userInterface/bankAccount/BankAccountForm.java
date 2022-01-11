package com.openclassroomsproject.paymybuddy.userInterface.bankAccount;

import com.openclassroomsproject.paymybuddy.backend.model.BankAccount;
import com.openclassroomsproject.paymybuddy.backend.service.IBankAccountService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class BankAccountForm extends FormLayout {
    private final TextField holderFirstName = new TextField("First name");
    private final TextField holderLastName = new TextField("Last name");
    private final TextField iban = new TextField("IBAN");
    private final TextField bic = new TextField("BIC");
    private final Button save = new Button("Save bank account");
    private final Binder<BankAccount> binder = new Binder<>(BankAccount.class);
    private final BankAccountView bankAccountView;
    private final IBankAccountService bankAccountService;

    public BankAccountForm(BankAccountView bankAccountView, IBankAccountService bankAccountService) {
        this.bankAccountView = bankAccountView;
        this.bankAccountService = bankAccountService;
        configureButtonSave();
        HorizontalLayout layout = new HorizontalLayout(save);
        add(holderFirstName, holderLastName, iban, bic, layout);
        binder.forField(holderFirstName).asRequired().bind("holderFirstName");
        binder.forField(holderLastName).asRequired().bind("holderLastName");
        binder.forField(iban).asRequired().bind("iban");
        binder.forField(bic).asRequired().bind("bic");
        save.addClickListener(buttonClickEvent -> {
            try {
                save();
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
    }

    private void save() throws ValidationException {
        BankAccount bankAccount = new BankAccount();
        binder.writeBean(bankAccount);
        boolean response = bankAccountService.addBankAccount(bankAccount);
        if (!response) {
            Notification notificationError = Notification.show("An account already exists, please delete it first ");
            notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else {
            Notification notificationSuccess = Notification.show("Bank account set up with success !");
            notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            bankAccountView.updateList();
        }
    }

    private void configureButtonSave() {
        save
                .getElement()
                .getStyle()
                .set("background-color", "#0275D8")
                .set("color", "white")
                .set("border-radius", "10px")
                .set("padding", "2% 6.25% 5% 6.25%")
                .set("font-weight", "bold");
    }
}