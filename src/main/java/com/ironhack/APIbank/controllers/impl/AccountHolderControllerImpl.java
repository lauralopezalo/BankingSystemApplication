package com.ironhack.APIbank.controllers.impl;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.controllers.interfaces.AccountHolderControllerInt;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.services.interfaces.AccountHolderServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-holder")
public class AccountHolderControllerImpl implements AccountHolderControllerInt {
    @Autowired
    private AccountHolderServiceInt accountHolderServiceInt;

    @GetMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceById(@PathVariable(name = "id") Long id){
        return accountHolderServiceInt.getAccountBalance(id);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Account transferMoney(@RequestBody TransferenceDTO transferenceDTO){
        return accountHolderServiceInt.transfer(transferenceDTO);
    }
}
