package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    @Test
    void applyMonthlyMaintenanceFee() {
        Checking checkingAccount = new Checking();
        checkingAccount.setBalance(new Money(new BigDecimal("1000")));
        checkingAccount.setMonthlyMaintenanceFee(new BigDecimal("10"));
        checkingAccount.setLastFeeUpdate(LocalDate.of(2023, 1, 1));

        checkingAccount.applyMonthlyMaintenanceFee();
        assertEquals(new BigDecimal("990.00"), checkingAccount.getBalance().getAmount());
        assertEquals(LocalDate.of(2023, 2, 1), checkingAccount.getLastFeeUpdate());
        checkingAccount.applyMonthlyMaintenanceFee();

        assertEquals(new BigDecimal("990.00"), checkingAccount.getBalance().getAmount());
        assertEquals(LocalDate.of(2023, 2, 1), checkingAccount.getLastFeeUpdate());

    }

    @Test
    void testApplyPenaltyFee() {
        Checking checking = new Checking();
        checking.setBalance(new Money(new BigDecimal("1100.00")));
        checking.setMinimumBalance(new BigDecimal("1000.00"));
        checking.applyPenaltyFee();

        assertEquals(new BigDecimal("1100.00"), checking.getBalance().getAmount());
        checking.getBalance().decreaseAmount(new Money(new BigDecimal("1000.00")));
        checking.applyPenaltyFee();
        assertEquals(new BigDecimal("60.00"), checking.getBalance().getAmount());
    }
}