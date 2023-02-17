package com.ironhack.finalproject.controllers.impl;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.finalproject.controllers.interfaces.AccountHolderControllerInt;
import com.ironhack.finalproject.models.accounts.Account;
import com.ironhack.finalproject.models.users.User;
import com.ironhack.finalproject.services.impl.AccountHolderServiceImpl;
import com.ironhack.finalproject.services.impl.CustomUserDetailsService;
import com.ironhack.finalproject.services.interfaces.AccountHolderServiceInt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

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
    public void transferMoney(@RequestBody TransferenceDTO transferenceDTO){
        accountHolderServiceInt.transfer(transferenceDTO);
    }
}
