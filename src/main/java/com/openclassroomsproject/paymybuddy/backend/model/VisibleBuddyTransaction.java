package com.openclassroomsproject.paymybuddy.backend.model;

import java.time.LocalDate;

public class VisibleBuddyTransaction {

    private int id;
    private double amount;
    private String description;
    private LocalDate date;
    private String connexionEmail;
    private double charges;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getConnexionEmail() {
        return connexionEmail;
    }

    public void setConnexionEmail(String connexionEmail) {
        this.connexionEmail = connexionEmail;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }
}