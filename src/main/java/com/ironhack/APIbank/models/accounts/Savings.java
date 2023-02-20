package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.enums.Status;
import com.ironhack.APIbank.models.users.AccountHolder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "savings")
public class Savings extends Account {
    private String secretKey;
    @NotNull
    private BigDecimal minimumBalance = new BigDecimal("1000.00").setScale(2, RoundingMode.HALF_DOWN);

    @NotNull
    private BigDecimal interestRate = new BigDecimal(0.0025);

    private LocalDate lastProfitUpdate = super.getCREATION_DATE();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Savings() {
    }

    public Savings(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        setInterestRate(interestRate);
        this.status = Status.ACTIVE;
    }

    public Savings(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal("1000.00").setScale(2, RoundingMode.HALF_DOWN);
        this.interestRate = new BigDecimal("0.0025");
        this.status = Status.ACTIVE;
    }

    public Money addInterest() {
        LocalDate currentDate = LocalDate.now();
        while ((lastProfitUpdate.isBefore(currentDate.minusYears(1)))) {
            BigDecimal interest = super.getBalance().getAmount().multiply(interestRate);
            interest = interest.setScale(2, RoundingMode.HALF_UP);
            super.getBalance().increaseAmount(interest);
            lastProfitUpdate = lastProfitUpdate.plusYears(1);
        }
        return super.getBalance();
    }

    public void applyPenaltyFee() {
        if (super.getBalance().getAmount().compareTo(minimumBalance) < 0) {
            super.getBalance().decreaseAmount(getPENALTY_FEE());
        }
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

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (minimumBalance.compareTo(new BigDecimal("100")) > 0 && minimumBalance.compareTo(new BigDecimal("1000")) <= 0) {
            this.minimumBalance = minimumBalance;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal("0")) >= 0 && interestRate.compareTo(new BigDecimal("0.5")) <= 0) {
            this.interestRate = interestRate;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getLastProfitUpdate() {
        return lastProfitUpdate;
    }

    public void setLastProfitUpdate(LocalDate lastProfitUpdate) {
        this.lastProfitUpdate = lastProfitUpdate;
    }
}
