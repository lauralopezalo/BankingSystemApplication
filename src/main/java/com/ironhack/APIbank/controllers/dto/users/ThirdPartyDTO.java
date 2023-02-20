package com.ironhack.APIbank.controllers.dto.users;

import java.math.BigDecimal;

public class ThirdPartyDTO {
    private Long id;
    private BigDecimal amount;
    private Long accountId;
    private String secretKey;

    public ThirdPartyDTO(Long id, BigDecimal amount, Long accountId, String secretKey) {
        this.id = id;
        this.amount = amount;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
