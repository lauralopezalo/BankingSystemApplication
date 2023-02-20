package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.enums.Status;
import com.ironhack.APIbank.models.users.AccountHolder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "checking")
public class Checking extends Account {
    private String secretKey;
    private final BigDecimal minimumBalance = new BigDecimal("250");
    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");
    private LocalDate lastFeeUpdate = super.getCREATION_DATE();

    @Enumerated(EnumType.STRING)
    private Status status;
    public Checking(){
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
    }


    public void applyMonthlyMaintenanceFee() {
        LocalDate currentDate = LocalDate.now();
        while ((lastFeeUpdate.isBefore(currentDate.minusMonths(1)))) {
            super.getBalance().decreaseAmount(monthlyMaintenanceFee);
            lastFeeUpdate = currentDate.plusMonths(1);;
        }
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

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getLastFeeUpdate() {
        return lastFeeUpdate;
    }

    public void setLastFeeUpdate(LocalDate lastFeeUpdate) {
        this.lastFeeUpdate = lastFeeUpdate;
    }
}
