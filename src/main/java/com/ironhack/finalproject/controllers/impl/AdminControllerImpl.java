package com.ironhack.finalproject.controllers.impl;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.CheckingDTO;
import com.ironhack.finalproject.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.finalproject.controllers.dto.accounts.SavingsDTO;
import com.ironhack.finalproject.controllers.interfaces.AdminControllerInt;
import com.ironhack.finalproject.models.accounts.Account;
import com.ironhack.finalproject.models.accounts.CreditCard;
import com.ironhack.finalproject.models.accounts.Savings;
import com.ironhack.finalproject.models.users.AccountHolder;
import com.ironhack.finalproject.models.users.Admin;
import com.ironhack.finalproject.models.users.ThirdParty;
import com.ironhack.finalproject.services.interfaces.AdminServiceInt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminControllerInt {
    @Autowired
    AdminServiceInt adminServiceInt;

    @PostMapping("/add-new")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addNewAdmin(@RequestBody @Valid Admin admin){
       return adminServiceInt.addAdmin(admin);
    }

    @PostMapping("/add-account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addNewAccountHolder(@RequestBody AccountHolder accountHolder){
        return adminServiceInt.addAccountHolder(accountHolder);
    }

    @PostMapping("/add-third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addNewThirdParty(@RequestBody ThirdParty thirdParty){
        return adminServiceInt.addThirdParty(thirdParty);
    }

    @PostMapping("/add-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addNewChecking(@RequestBody CheckingDTO accountDTO){
        return adminServiceInt.addChecking(accountDTO);
    }

    @PostMapping("/add-savings-account")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addNewSavings(@RequestBody SavingsDTO savingsDTO){
        return adminServiceInt.addSavings(savingsDTO);
    }

    @PostMapping("/add-credit-card-account")
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
