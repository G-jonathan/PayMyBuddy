package com.openclassroomsproject.paymybuddy.userInterface;

import com.openclassroomsproject.paymybuddy.backend.service.IConnexionService;
import com.openclassroomsproject.paymybuddy.backend.service.IUserAccountService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;

@Route(value = "contact", layout = MainLayout.class)
public class ContactView extends VerticalLayout {
    TextField textField = new TextField();
    private final IConnexionService connexionService;
    private final IUserAccountService userAccountService;

    public ContactView(IConnexionService connexionService, IUserAccountService userAccountService) {
        this.connexionService = connexionService;
        this.userAccountService = userAccountService;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        Button addConnexionButton = new Button("Add Connexion", buttonClickEvent -> {addAConnexion();});
        stylizeAddConnexionButton(addConnexionButton);

        HorizontalLayout addAConnexionLayout = new HorizontalLayout(textField,addConnexionButton);
        stylizeAddAConnexion(addAConnexionLayout);
        add(addAConnexionLayout);
    }

    public void stylizeAddAConnexion(HorizontalLayout horizontalLayout) {
        horizontalLayout.getStyle()
                .set("background-color", "#F3F5F7")
                .set("padding", "2%");
    }

    public void stylizeAddConnexionButton(Button button) {
        button.getElement()
                .getStyle()
                .set("background-color", "#0275D8")
                .set("color", "white")
                .set("border-radius", "10px")
                .set("font-weight", "bold");
    }

    public void addAConnexion() {
        String textValue = textField.getValue();
        boolean userExist = userAccountService.emailUserAlreadyExist(textValue);
        boolean connexionAlreadyExist = connexionService.findConnexionByUserAccountEmailAndConnexionEmail(textValue);
        if (!userExist) {
            Notification notificationError = Notification.show("User don't exist");
            notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);

        } else if (connexionAlreadyExist) {
            Notification notificationError = Notification.show("Connexion already exist");
            notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else {
            connexionService.addConnection(textValue);
            Notification notificationSuccess = Notification.show("Connexion add successful !");
            notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
    }
}
