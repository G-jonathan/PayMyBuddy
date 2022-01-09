package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "connexion")
public class Connexion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "user_account_email")
    private String userAccountEmail;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "connexion_email")
    private String connexionEmail;

    public int getId() {
        return id;
    }

    public String getUserAccountEmail() {
        return userAccountEmail;
    }

    public String getConnexionEmail() {
        return connexionEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        this.userAccountEmail = userAccountEmail;
    }

    public void setConnexionEmail(String connexionEmail) {
        this.connexionEmail = connexionEmail;
    }
}