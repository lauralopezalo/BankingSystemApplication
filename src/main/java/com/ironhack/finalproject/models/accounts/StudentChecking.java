package com.ironhack.finalproject.models.accounts;

import com.ironhack.finalproject.enums.Status;
import com.ironhack.finalproject.models.users.AccountHolder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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
