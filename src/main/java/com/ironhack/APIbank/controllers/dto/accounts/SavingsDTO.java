package com.ironhack.APIbank.controllers.dto.accounts;

import java.math.BigDecimal;
import java.util.Currency;

public class SavingsDTO {
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String secretKey;

    public SavingsDTO(Long primaryOwnerId, Long secondaryOwnerId, String secretKey) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.secretKey = secretKey;
    }

    public SavingsDTO() {

    }

    public SavingsDTO(Long id, Object o, BigDecimal bigDecimal, Currency usd, String savingsSecretKey) {
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
}
