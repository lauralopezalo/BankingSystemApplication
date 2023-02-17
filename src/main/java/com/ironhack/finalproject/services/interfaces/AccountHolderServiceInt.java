package com.ironhack.finalproject.services.interfaces;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.finalproject.models.accounts.Account;
import com.ironhack.finalproject.models.accounts.Checking;
import com.ironhack.finalproject.models.accounts.CreditCard;
import com.ironhack.finalproject.models.accounts.Savings;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public interface AccountHolderServiceInt {
    Money getAccountBalance(Long accountId);
    void transfer(TransferenceDTO transferenceDTO);

}
