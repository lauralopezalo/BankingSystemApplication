package com.ironhack.APIbank.controllers.dto.users;

import com.ironhack.APIbank.embeddable.Address;

import java.time.LocalDate;

public class AccountHolderDTO {
    //@NotNull
    private String name;
    //@NotNull
    private String password;

    private LocalDate birthDate;

    private Address primaryAddress;

    private Address mailAddress;



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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(Address mailAddress) {
        this.mailAddress = mailAddress;
    }
}
