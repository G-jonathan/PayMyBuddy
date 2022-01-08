package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "buddy_transaction")
public class BuddyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private double amount;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private double charges;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String description;

    @NotNull
    @NotEmpty
    private LocalDate date;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "user_account_email")
    private String userAccountEmail;

    @NotNull
    @NotEmpty
    @Column(name = "connexion_id")
    private int connexionId;

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getCharges() {
        return charges;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public int getConnexionId() {
        return connexionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }

    public void setConnexionId(int connexionId) {
        this.connexionId = connexionId;
    }
}