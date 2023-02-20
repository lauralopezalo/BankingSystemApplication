package com.ironhack.APIbank.controllers.interfaces;

import com.ironhack.APIbank.controllers.dto.accounts.CheckingDTO;
import com.ironhack.APIbank.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.APIbank.controllers.dto.accounts.SavingsDTO;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.CreditCard;
import com.ironhack.APIbank.models.accounts.Savings;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Admin;
import com.ironhack.APIbank.models.users.ThirdParty;

public interface AdminControllerInt {

    Admin addNewAdmin(Admin admin);

    AccountHolder addNewAccountHolder(AccountHolder accountHolder);

    ThirdParty addNewThirdParty(ThirdParty thirdParty);

    Account addNewChecking(CheckingDTO checkingDTO);

    Savings addNewSavings(SavingsDTO savingsDTO);

    CreditCard addNewCreditCardAccount(CreditCardDTO creditCardDTO);

    Account findAccountById(Long id);

    Money findAccountBalance(Long id);

    Account updateAccountBalanceById(Long id, Money money);

    void deleteAccount(Long id);

    void deleteUser(Long id);

}
