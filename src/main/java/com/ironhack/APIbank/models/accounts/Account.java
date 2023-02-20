package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

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

    private BigDecimal penaltyFee = new BigDecimal("40");

    @Column(name = "creation_date")
    private final LocalDate CREATION_DATE = LocalDate.now();


    public Account() {
    }

//    public Account(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance) {
//        this.balance = new Money(balance.getCurrency());
//        this.primaryOwner = primaryOwner;
//        this.secondaryOwner = secondaryOwner;
//    }

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

    public BigDecimal getpenaltyFee() {
        return penaltyFee;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setBalance(BigDecimal amount, Currency currency) {
    }


}
