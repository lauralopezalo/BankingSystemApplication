package com.ironhack.finalproject.models.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Money balance;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;
    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;

    private final BigDecimal PENALTY_FEE = new BigDecimal(40);

    @Column(name = "creation_date")
    private final LocalDate CREATION_DATE = LocalDate.now();


    public Account() {
    }

    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = new Money(new BigDecimal(0));
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPENALTY_FEE() {
        return PENALTY_FEE;
    }

    public LocalDate getCREATION_DATE() {
        return CREATION_DATE;
    }

}
