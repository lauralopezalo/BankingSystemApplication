package com.ironhack.APIbank.controllers.dto.accounts;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;

import java.math.BigDecimal;

public class CheckingDTO {
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;


//    public CheckingDTO(Long primaryOwnerId, Long secondaryOwnerId, String secretKey, Money balance) {
//        this.primaryOwnerId = primaryOwnerId;
//        this.secondaryOwnerId = secondaryOwnerId;
//        this.secretKey = secretKey;
//        this.balance = balance;
//    }

//    public CheckingDTO() {
//    }
//
//    public CheckingDTO(Long primaryOwnerId, Long secondaryOwnerId, String secretKey, BigDecimal amount, String currency) {
//        this.primaryOwnerId = primaryOwnerId;
//        this.secondaryOwnerId = secondaryOwnerId;
//        this.secretKey = secretKey;
//        this.amount = amount;
//        this.currency = currency;
//    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }


    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
