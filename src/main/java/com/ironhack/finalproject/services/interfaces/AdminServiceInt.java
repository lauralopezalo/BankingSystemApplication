package com.ironhack.finalproject.services.interfaces;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.CheckingDTO;
import com.ironhack.finalproject.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.finalproject.controllers.dto.accounts.SavingsDTO;
import com.ironhack.finalproject.models.accounts.*;
import com.ironhack.finalproject.models.users.AccountHolder;
import com.ironhack.finalproject.models.users.Admin;
import com.ironhack.finalproject.models.users.ThirdParty;


public interface AdminServiceInt {
    Admin addAdmin(Admin admin);

    AccountHolder addAccountHolder(AccountHolder accountHolder);

    ThirdParty addThirdParty(ThirdParty thirdParty);

    Account addChecking(CheckingDTO checkingDTO);

    Savings addSavings(SavingsDTO savingsDTO);

    CreditCard addCreditCardAccount(CreditCardDTO creditCardDTO);

    Money getAccountBalance(Long id);

    Account updateAccountBalance(Long account_id, Money money);

    void deleteAccount(Long id);

    void deleteUser(Long id);

    Account getAccount(Long id);
}
