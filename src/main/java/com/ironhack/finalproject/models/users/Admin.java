package com.ironhack.finalproject.models.users;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String password, String username) {
        super(name, password, username);
    }
}
