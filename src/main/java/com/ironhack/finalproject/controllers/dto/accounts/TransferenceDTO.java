package com.ironhack.finalproject.controllers.dto.accounts;

import java.math.BigDecimal;

public class TransferenceDTO {
    public Long sourceAccountId;
    public Long targetAccountId;
    public BigDecimal amount;
    public String primaryOwner;
    public String secundaryOwner;

    public TransferenceDTO() {
    }

    public TransferenceDTO(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(String primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public String getSecundaryOwner() {
        return secundaryOwner;
    }

    public void setSecundaryOwner(String secundaryOwner) {
        this.secundaryOwner = secundaryOwner;
    }
}
