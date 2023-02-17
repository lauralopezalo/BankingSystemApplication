package com.ironhack.finalproject.controllers.dto.users;

import com.ironhack.finalproject.models.users.User;
import jakarta.validation.constraints.NotNull;

public class AdminDTO{
    //@NotNull
    private String name;
    //@NotNull
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
