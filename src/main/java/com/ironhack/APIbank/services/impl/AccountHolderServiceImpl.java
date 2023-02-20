package com.ironhack.APIbank.services.impl;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.Checking;
import com.ironhack.APIbank.models.accounts.CreditCard;
import com.ironhack.APIbank.models.accounts.Savings;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.accounts.CheckingRepository;
import com.ironhack.APIbank.repositories.accounts.CreditCardRepository;
import com.ironhack.APIbank.repositories.accounts.SavingsRepository;
import com.ironhack.APIbank.repositories.users.AccountHolderRepository;
import com.ironhack.APIbank.repositories.users.UserRepository;
import com.ironhack.APIbank.services.interfaces.AccountHolderServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderServiceImpl implements AccountHolderServiceInt {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
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
        } else if(account instanceof Checking ){
            checkingRepository.findById(accountId).get().applyMonthlyMaintenanceFee();
        } else if (account instanceof CreditCard) {
            creditCardRepository.findById(accountId).get().addInterest();
        }
        return account.getBalance();
    }



    public Account transfer(TransferenceDTO transferenceDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account sourceAccount = accountRepository.findById(transferenceDTO.getSourceAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Source account not found"));

        Account targetAccount = accountRepository.findById(transferenceDTO.getTargetAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target account not found"));

        if (!(sourceAccount.getPrimaryOwner().getName().equals(username)
                || (sourceAccount.getSecondaryOwner() != null && sourceAccount.getSecondaryOwner().getName().equals(username)))) {
            throw new AccessDeniedException("Access is denied");
        }

        if (!(transferenceDTO.getTargetAccountOwner().equals(targetAccount.getPrimaryOwner().getName())
                || transferenceDTO.getTargetAccountOwner().equals(targetAccount.getSecondaryOwner().getName()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The target account holder's name does not match");
        }

        if (sourceAccount.getBalance().getAmount().compareTo(transferenceDTO.getAmount()) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds in source account");
        }

        if (sourceAccount instanceof Checking) {
            if (sourceAccount.getBalance().getAmount().compareTo(((Checking) sourceAccount).getMinimumBalance()) > 0
                    && sourceAccount.getBalance().getAmount().subtract(transferenceDTO.getAmount()).compareTo(((Checking) sourceAccount).getMinimumBalance()) < 0) {
                sourceAccount.setBalance(new Money(sourceAccount.getBalance().decreaseAmount(sourceAccount.getpenaltyFee())));
            }
        }
        if (sourceAccount instanceof Savings) {
            if (sourceAccount.getBalance().getAmount().compareTo(((Savings) sourceAccount).getMinimumBalance()) > 0
                    && sourceAccount.getBalance().getAmount().subtract(transferenceDTO.getAmount()).compareTo(((Savings) sourceAccount).getMinimumBalance()) < 0) {
                sourceAccount.setBalance(new Money(sourceAccount.getBalance().decreaseAmount(sourceAccount.getpenaltyFee())));
            }
        }
        sourceAccount.getBalance().decreaseAmount(transferenceDTO.getAmount());
        targetAccount.getBalance().increaseAmount(transferenceDTO.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        return sourceAccount;
    }

}
