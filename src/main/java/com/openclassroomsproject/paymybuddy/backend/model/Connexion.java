package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "connexion")
public class Connexion {

    @Id
    private int id;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "user_account_email")
    private String UserAccountEmail;

    @NotNull
    @NotEmpty
    @Size(max = 254)
    @Column(name = "connexion_email")
    private String connexionEmail;

    public int getId() {
        return id;
    }

    public String getUserAccountEmail() {
        return UserAccountEmail;
    }

    public String getConnexionEmail() {
        return connexionEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserAccountEmail(String userAccountEmail) {
        UserAccountEmail = userAccountEmail;
    }

    public void setConnexionEmail(String connexionEmail) {
        this.connexionEmail = connexionEmail;
    }
}