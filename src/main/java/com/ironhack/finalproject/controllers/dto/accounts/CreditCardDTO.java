package com.ironhack.finalproject.controllers.dto.accounts;

import java.math.BigDecimal;

public class CreditCardDTO {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;


    public CreditCardDTO(Long primaryOwnerId, Long secondaryOwnerId, String secretKey, BigDecimal creditLimit, BigDecimal interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.secretKey = secretKey;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

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

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public CreditCardDTO() {
    }
}
