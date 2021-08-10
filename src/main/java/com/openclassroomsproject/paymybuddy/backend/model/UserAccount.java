package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_Account")
public class UserAccount {

    @Id
    @Email
    @NotNull
    @NotEmpty
    @Size(max = 254)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String password;

    @NotNull
    @NotEmpty
    @Size(max = 1000)
    private double balance;

    @NotNull
    @NotEmpty
    @Column(name = "is_activated")
    private boolean isActivated;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}