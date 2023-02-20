package com.ironhack.APIbank.models.accounts;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.enums.Status;
import com.ironhack.APIbank.models.users.AccountHolder;
import jakarta.persistence.*;

import java.util.Currency;

@Entity
@Table(name = "studentChecking")
public class StudentChecking extends Account {
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
