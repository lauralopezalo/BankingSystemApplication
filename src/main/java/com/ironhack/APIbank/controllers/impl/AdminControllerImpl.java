package com.ironhack.APIbank.controllers.impl;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.controllers.dto.accounts.CheckingDTO;
import com.ironhack.APIbank.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.APIbank.controllers.dto.accounts.SavingsDTO;
import com.ironhack.APIbank.controllers.interfaces.AdminControllerInt;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.CreditCard;
import com.ironhack.APIbank.models.accounts.Savings;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Admin;
import com.ironhack.APIbank.models.users.ThirdParty;
import com.ironhack.APIbank.services.interfaces.AdminServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminControllerInt {
    @Autowired
    AdminServiceInt adminServiceInt;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addNewAdmin(@RequestBody @Valid Admin admin){
       return adminServiceInt.addAdmin(admin);
    }

    @PostMapping("/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addNewAccountHolder(@RequestBody AccountHolder accountHolder){
        return adminServiceInt.addAccountHolder(accountHolder);
    }

    @PostMapping("/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addNewThirdParty(@RequestBody ThirdParty thirdParty){
        return adminServiceInt.addThirdParty(thirdParty);
    }

    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addNewChecking(@RequestBody CheckingDTO checkingDTO){
        return adminServiceInt.addChecking(checkingDTO);
    }

    @PostMapping("/savings-account")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addNewSavings(@RequestBody SavingsDTO savingsDTO){
        return adminServiceInt.addSavings(savingsDTO);
    }

    @PostMapping("/credit-card-account")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addNewCreditCardAccount(@RequestBody CreditCardDTO creditCardDTO){
        return adminServiceInt.addCreditCardAccount(creditCardDTO);
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long id) {
        return adminServiceInt.getAccount(id);
    }

    @GetMapping("/account/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Money findAccountBalance(@PathVariable Long id) {
        return adminServiceInt.getAccountBalance(id);
    }

    @PatchMapping("/account/update-balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountBalanceById(@PathVariable Long id, @RequestBody Money money){
        return adminServiceInt.updateAccountBalance(id, money);
    }

    @DeleteMapping("/account-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id){
        adminServiceInt.deleteAccount(id);
    }

    @DeleteMapping("/user-delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        adminServiceInt.deleteUser(id);
    }


}
