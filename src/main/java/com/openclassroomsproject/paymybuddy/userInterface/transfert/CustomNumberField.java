package com.openclassroomsproject.paymybuddy.userInterface.transfert;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.apache.commons.lang3.StringUtils;

@CssImport(value = "./my-styles/customNumberField.css", themeFor = "vaadin-number-field")
public class CustomNumberField extends HorizontalLayout {
    private static final int DEFAULT_VALUE = 0;
    private static final int DEFAULT_INCREMENT = 1;
    private int incrementValue;
    private int decrementValue;
    private int numericValue;
    TextField textField = new TextField();

    public CustomNumberField() {
        this(DEFAULT_VALUE, DEFAULT_INCREMENT, -DEFAULT_INCREMENT);
    }

    public CustomNumberField(int value, int incrementValue, int decrementValue) {
        setNumericValue(value);
        this.incrementValue = incrementValue;
        this.decrementValue = decrementValue;
        addClassName("v8");
        //textField.getElement().setAttribute("theme", "numeric");
        textField.setPattern("-?[0-9]*");
        textField.getElement().getStyle().set("text-align", "center");
        textField.setPreventInvalidInput(true);
        VerticalLayout verticalLayoutTextField = new VerticalLayout(textField);
        textField.addClassName("v9");
        textField.addValueChangeListener(event -> {
            String content = event.getSource().getValue();
            if (StringUtils.isNumeric(content)) {
                setNumericValue(Integer.parseInt(content));
            } else {
                setNumericValue(DEFAULT_VALUE);
            }
        });
        Icon iconUp = VaadinIcon.CARET_UP.create();
        iconUp.addClassName("v11");
        Icon iconDown = VaadinIcon.CARET_DOWN.create();
        iconDown.addClassName("v12");

        Button addButton = new Button("+", event -> {
            setNumericValue(numericValue + incrementValue);
        });
        Button subtractButton = new Button("-", event -> {
            setNumericValue(numericValue + decrementValue);
        });
        VerticalLayout verticalLayout = new VerticalLayout(iconUp, iconDown);
        verticalLayout.addClassName("v13");

        Icon iconMoney = VaadinIcon.EURO.create();
        iconMoney.addClassName("v15");
        iconMoney.setSize("20px");
        HorizontalLayout horizontalLayoutMoney = new HorizontalLayout(textField, iconMoney);
        horizontalLayoutMoney.addClassName("v14");
        add(horizontalLayoutMoney, verticalLayout);
    }

    public void setNumericValue(int value) {
        numericValue = value;
        textField.setValue(String.valueOf(value));
    }
}