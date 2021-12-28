package com.openclassroomsproject.paymybuddy.userInterface.registration;

import com.openclassroomsproject.paymybuddy.backend.model.UserAccount;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Registration")
@Route(value = "registration")
public class RegistrationForm extends VerticalLayout {
    private boolean enablePasswordValidation;
    private final IUserAccountService userAccountService;
    private Binder<UserAccount> validationBinder;
    private final PasswordField mainPasswordField;
    private final PasswordField repeatPasswordField;

    public RegistrationForm(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
        H3 title = new H3("Registration form");
        TextField firstnameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        EmailField emailField = new EmailField("Email");
        mainPasswordField = new PasswordField("Password");
        repeatPasswordField = new PasswordField("Repeat Password");
        Span errorMessage = new Span();
        configureErrorMessage(errorMessage);
        Button submitButton = new Button("Submit");
        FormLayout formLayout = new FormLayout(title, firstnameField, lastNameField, emailField, mainPasswordField, repeatPasswordField, errorMessage, submitButton);
        configureFormLayout(formLayout);
        add(formLayout);
        validationBinder = new Binder<>(UserAccount.class);
        validationBinder.forField(firstnameField).asRequired().bind("firstName");
        validationBinder.forField(lastNameField).asRequired().bind("lastName");
        validationBinder.forField(emailField).withValidator(this::validateEmail).asRequired().bind("email");
        validationBinder.forField(mainPasswordField).asRequired().withValidator(this::passwordValidator).bind("password");
        repeatPasswordField.addValueChangeListener(e -> {
            enablePasswordValidation = true;
            validationBinder.validate();
        });
        submitButton.addClickListener(e -> {
            try {
                UserAccount userAccountBean = new UserAccount();
                validationBinder.writeBean(userAccountBean);
                userAccountService.createUserAccount(userAccountBean);
                showSuccess(userAccountBean);
            } catch (ValidationException validationException) {
                validationException.printStackTrace();
            }
        });
    }

    private ValidationResult validateEmail(String email, ValueContext context) {
        String errorMessage = "Email already exist";
        if (userAccountService.emailUserAlreadyExist(email)) {
            return ValidationResult.error(errorMessage);
        } else {
            return ValidationResult.ok();
        }
    }

    private ValidationResult passwordValidator(String mainPassword, ValueContext ctx) {
        if (mainPassword == null || mainPassword.length() > 100 || mainPassword.length() < 10) {
            return ValidationResult.error("The password must be between 10 and 100 characters");
        }
        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        String repeatPassword = repeatPasswordField.getValue();
        if (mainPassword.equals(repeatPassword)) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Passwords do not match");
    }

    private void showSuccess(UserAccount userAccountBean) {
        Notification notification = Notification.show("Account successfully created. Welcome " + userAccountBean.getFirstName() + " " + userAccountBean.getLastName() + " !");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        // TODO REDIRECT TO ANOTHER VIEW
    }

    private void configureFormLayout(FormLayout formLayout) {
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");
    }

    private void configureErrorMessage(Span errorMessage) {
        errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
        errorMessage.getStyle().set("padding", "15px 0");
    }
}