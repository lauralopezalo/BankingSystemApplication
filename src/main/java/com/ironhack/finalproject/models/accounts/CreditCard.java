package com.ironhack.finalproject.models.accounts;

import com.ironhack.finalproject.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "CreditCard")
public class CreditCard extends Account {

    @NotNull
//    @DecimalMin(value = "100", inclusive = true)
//    @DecimalMax(value = "100000", inclusive = true)
    private BigDecimal creditLimit = new BigDecimal("100");

    @NotNull
//    @DecimalMin(value = "0.10", inclusive = true)
//    @DecimalMax(value = "0.20", inclusive = true)
    private BigDecimal interestRate = new BigDecimal("0.2");

    private LocalDate lastProfitUpdate;

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
        if ((lastProfitUpdate == null && super.getCREATION_DATE().isBefore(currentDate.minusMonths(1)))
                || lastProfitUpdate.isBefore(currentDate.minusMonths(1))) {
            BigDecimal interest = super.getBalance().getAmount().multiply(interestRate);
            super.getBalance().increaseAmount(interest);
            lastProfitUpdate = currentDate;
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
