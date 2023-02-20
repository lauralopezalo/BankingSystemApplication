package com.ironhack.APIbank.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.APIbank.embeddable.Address;
import com.ironhack.APIbank.models.accounts.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User {

    private LocalDate birthDate;

    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "mail_street")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mail_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "mail_city")),
            @AttributeOverride(name = "country", column = @Column(name = "mail_country"))
    })
    private Address mailAddress;

    @NotNull
    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    private List<Account> primaryOwnerAccount = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner", cascade = CascadeType.ALL)
    private List<Account> secondaryOwnerAccount = new ArrayList<>();


    public AccountHolder() {
    }


    public AccountHolder(String name, String password, String username, LocalDate birthDate, Address primaryAddress, Address mailAddress) {
        super(name, password, username);
        this.birthDate = birthDate;
        this.primaryAddress = primaryAddress;
        this.mailAddress = mailAddress;
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

    public List<Account> getPrimaryOwnerAccount() {
        return primaryOwnerAccount;
    }

    public void setPrimaryOwnerAccount(List<Account> primaryOwnerAccount) {
        this.primaryOwnerAccount = primaryOwnerAccount;
    }

    public List<Account> getSecondaryOwnerAccount() {
        return secondaryOwnerAccount;
    }

    public void setSecondaryOwnerAccount(List<Account> secondaryOwnerAccount) {
        this.secondaryOwnerAccount = secondaryOwnerAccount;
    }



}

