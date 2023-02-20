package com.ironhack.APIbank.controllers.dto.users;

import java.math.BigDecimal;

public class ThirdPartyDTO {
    private Long id;
//    private String thirdPartyName;
    private BigDecimal amount;
    private Long accountId;
    private String secretKey;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

//    public String getThirdPartyName() {
//        return thirdPartyName;
//    }
//
//    public void setThirdPartyName(String thirdPartyName) {
//        this.thirdPartyName = thirdPartyName;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public String getHashedKey() {
//        return hashedKey;
//    }
//
//    public void setHashedKey(String hashedKey) {
//        this.hashedKey = hashedKey;
//    }
}
