package com.ironhack.APIbank.models.users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String password, String username) {
        super(name, password, username);
    }
}
