package com.ironhack.APIbank.controllers.dto.accounts;

import java.math.BigDecimal;
import java.util.Currency;

public class BalanceDTO {

    private Currency currency;
    private BigDecimal amount;

    public BigDecimal increaseAmount(BigDecimal addAmount) {
        setAmount(this.amount.add(addAmount));
        return this.amount;
    }


    public BigDecimal decreaseAmount(BigDecimal addAmount) {
        setAmount(this.amount.subtract(addAmount));
        return this.amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
