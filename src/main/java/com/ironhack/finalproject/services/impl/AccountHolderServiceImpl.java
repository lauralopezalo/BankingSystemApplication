package com.ironhack.finalproject.services.impl;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.finalproject.models.accounts.Account;
import com.ironhack.finalproject.models.accounts.Checking;
import com.ironhack.finalproject.models.accounts.CreditCard;
import com.ironhack.finalproject.models.accounts.Savings;
import com.ironhack.finalproject.models.users.AccountHolder;
import com.ironhack.finalproject.models.users.User;
import com.ironhack.finalproject.repositories.accounts.AccountRepository;
import com.ironhack.finalproject.repositories.accounts.CreditCardRepository;
import com.ironhack.finalproject.repositories.accounts.SavingsRepository;
import com.ironhack.finalproject.repositories.users.AccountHolderRepository;
import com.ironhack.finalproject.repositories.users.UserRepository;
import com.ironhack.finalproject.services.interfaces.AccountHolderServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountHolderServiceImpl implements AccountHolderServiceInt {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;


    public Money getAccountBalance(Long accountId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if (!(account.getPrimaryOwner().getName().equals(username)
                || (account.getSecondaryOwner() != null && account.getSecondaryOwner().getName().equals(username)))) {
            throw new AccessDeniedException("Access is denied");
        }
        if (account instanceof Savings) {
            savingsRepository.findById(accountId).get().addInterest();
        } else if (account instanceof CreditCard) {
            creditCardRepository.findById(accountId).get().addInterest();
        }
        return account.getBalance();
    }



    public void transfer(TransferenceDTO transferenceDTO) {

        Account sourceAccount = accountRepository.findById(transferenceDTO.getSourceAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Source account not found"));

        Account targetAccount = accountRepository.findById(transferenceDTO.getTargetAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target account not found"));

//        if (new BigDecimal(String.valueOf(sourceAccount.getBalance())).compareTo(transferenceDTO.getAmount()) < 0) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds in source account");
//        }

        if (sourceAccount.getBalance().getAmount().compareTo(transferenceDTO.getAmount()) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds in source account");
        }

        sourceAccount.getBalance().decreaseAmount(transferenceDTO.getAmount());
        targetAccount.getBalance().increaseAmount(transferenceDTO.getAmount());

        if (sourceAccount instanceof Checking) {
            if (sourceAccount.getBalance().getAmount().compareTo(((Checking) sourceAccount).getMinimumBalance()) < 0) {
                sourceAccount.setBalance(new Money(sourceAccount.getBalance().decreaseAmount(sourceAccount.getPENALTY_FEE())));
            }
        }
        if (sourceAccount instanceof Savings) {
            if (sourceAccount.getBalance().getAmount().compareTo(((Savings) sourceAccount).getMinimumBalance()) < 0) {
                sourceAccount.setBalance(new Money(sourceAccount.getBalance().decreaseAmount(sourceAccount.getPENALTY_FEE())));
            }
        }

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }

}
