package com.ironhack.APIbank.services.interfaces;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.controllers.dto.accounts.CheckingDTO;
import com.ironhack.APIbank.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.APIbank.controllers.dto.accounts.SavingsDTO;
import com.ironhack.APIbank.models.accounts.*;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Admin;
import com.ironhack.APIbank.models.users.ThirdParty;


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
