package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "CreditCard")
public class CreditCard extends Account {

    @NotNull
    private BigDecimal creditLimit = new BigDecimal("100");

    @NotNull
    private BigDecimal interestRate = new BigDecimal("0.2");

    private LocalDate lastProfitUpdate = super.getCREATION_DATE();

    public CreditCard() {}

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(primaryOwner, secondaryOwner);
        this.creditLimit = new BigDecimal("100").setScale(2, RoundingMode.HALF_DOWN);
        this.interestRate = new BigDecimal("0.2").setScale(4, RoundingMode.HALF_DOWN);
        this.lastProfitUpdate = LocalDate.now();
    }

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate, LocalDate lastProfitUpdate) {
        super(primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
        this.lastProfitUpdate = lastProfitUpdate;
    }


    public void addInterest() {
        LocalDate currentDate = LocalDate.now();
        while ((lastProfitUpdate.isBefore(currentDate.minusMonths(1)))) {
            BigDecimal interest = super.getBalance().getAmount().multiply(interestRate);
            interest = interest.setScale(2, RoundingMode.HALF_UP);
            super.getBalance().increaseAmount(interest);
            lastProfitUpdate = lastProfitUpdate.plusMonths(1);
        }
    }

    public BigDecimal getCreditLimit(BigDecimal creditLimit) {
        return this.creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit.compareTo(new BigDecimal("100")) > 0 && creditLimit.compareTo(new BigDecimal("100000")) <= 0) {
            this.creditLimit = creditLimit;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal("0.1")) >= 0 && interestRate.compareTo(new BigDecimal("0.2")) <= 0) {
            this.interestRate = interestRate;
        }
    }

    public LocalDate getLastProfitUpdate() {
        return lastProfitUpdate;
    }

    public void setLastProfitUpdate(LocalDate lastProfitUpdate) {
        this.lastProfitUpdate = lastProfitUpdate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
}
