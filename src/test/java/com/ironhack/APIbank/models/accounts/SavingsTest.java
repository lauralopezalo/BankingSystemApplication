package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SavingsTest {
    Savings savings = new Savings();
    @BeforeEach
    void setUp() {
        savings.setBalance(new Money(new BigDecimal("1100.00")));
        savings.setMinimumBalance(new BigDecimal("1000.00"));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addInterest() {
        LocalDate currentDate = LocalDate.now();
        savings.setLastProfitUpdate(currentDate.minusYears(2));
        savings.addInterest();
        assertEquals(new BigDecimal("1102.75"), savings.getBalance().getAmount());
    }

    @Test
    void applyPenaltyFee() {
        savings.applyPenaltyFee();
        assertEquals(new BigDecimal("1100.00"), savings.getBalance().getAmount());
        savings.getBalance().decreaseAmount(new Money(new BigDecimal("1000.00")));
        savings.applyPenaltyFee();
        assertEquals(new BigDecimal("60.00"), savings.getBalance().getAmount());
    }
}