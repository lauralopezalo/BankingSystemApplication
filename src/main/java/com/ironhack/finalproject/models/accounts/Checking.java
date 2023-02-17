package com.ironhack.finalproject.models.accounts;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.enums.Status;
import com.ironhack.finalproject.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Entity
@Table(name = "checking")
public class Checking extends Account {
    private String secretKey;
    private final BigDecimal minimumBalance = new BigDecimal("250");
    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");
    @Enumerated(EnumType.STRING)
    private Status status;
    public Checking(){
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
