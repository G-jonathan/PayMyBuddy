package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "holder_first_name")
    private String holderFirstName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "holder_last_name")
    private String holderLastName;

    @NotNull
    @NotEmpty
    @Size(max = 31)
    private String iban;

    @NotNull
    @NotEmpty
    @Size(max = 11)
    private String bic;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "user_account_email")
    private String userAccountEmail;

    public int getId() {
        return id;
    }

    public String getHolderFirstName() {
        return holderFirstName;
    }

    public String getHolderLastName() {
        return holderLastName;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHolderFirstName(String holderFirstName) {
        this.holderFirstName = holderFirstName;
    }

    public void setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }
}