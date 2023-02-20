package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    CreditCard creditCard = new CreditCard();
    @BeforeEach
    void setUp() {
        creditCard.setBalance(new Money(new BigDecimal("1000.00")));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addInterest() {
        LocalDate currentDate = LocalDate.now();
        creditCard.setLastProfitUpdate(currentDate.minusMonths(2));
        creditCard.addInterest();
        assertEquals(new BigDecimal("1200.00"), creditCard.getBalance().getAmount());
    }
}