package com.openclassroomsproject.paymybuddy.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "authorities")
public class Authorities {

    @Id
    @NotNull
    @NotEmpty
    @Size(max = 254)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String authority;

    public String getEmail() {
        return email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}