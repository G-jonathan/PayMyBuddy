package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

    public enum BankTransactionType {
        Deposit, WithDrawal
    }

    @Id
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
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private BankTransaction.BankTransactionType transactionType;

    @NotNull
    @NotEmpty
    @Column(name = "bank_account_id")
    private int bankAccountId;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "user_account_email")
    private String userAccountEmail;

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

    public BankTransactionType getTransactionType() {
        return transactionType;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
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

    public void setTransactionType(BankTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }
}