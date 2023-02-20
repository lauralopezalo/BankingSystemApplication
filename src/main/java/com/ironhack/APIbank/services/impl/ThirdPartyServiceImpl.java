package com.ironhack.APIbank.services.impl;

import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.controllers.dto.users.ThirdPartyDTO;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.Checking;
import com.ironhack.APIbank.models.accounts.Savings;
import com.ironhack.APIbank.models.users.ThirdParty;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.users.ThirdPartyRepository;
import com.ironhack.APIbank.services.interfaces.ThirdPartyServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyServiceInt {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Account transferMoney(String hashedKey, ThirdPartyDTO thirdPartyDTO) {

        Account account = accountRepository.findById(thirdPartyDTO.getAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        ThirdParty thirdParty = thirdPartyRepository.findById(thirdPartyDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ThirdParty not found"));

        if (thirdPartyDTO.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer amount must be non-zero");
        }

//        Charge amount to customer
        if (thirdPartyDTO.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            if (account.getBalance().getAmount().compareTo(thirdPartyDTO.getAmount()) < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds in source account");
            }
            if (account instanceof Checking) {
                if (account.getBalance().getAmount().compareTo(((Checking) account).getMinimumBalance()) > 0
                        && account.getBalance().getAmount().subtract(thirdPartyDTO.getAmount()).compareTo(((Checking) account).getMinimumBalance()) < 0) {
                    account.setBalance(new Money(account.getBalance().decreaseAmount(account.getPENALTY_FEE())));
                }
            }
            if (account instanceof Savings) {
                if (account.getBalance().getAmount().compareTo(((Savings) account).getMinimumBalance()) > 0
                        && account.getBalance().getAmount().subtract(thirdPartyDTO.getAmount()).compareTo(((Savings) account).getMinimumBalance()) < 0) {
                    account.setBalance(new Money(account.getBalance().decreaseAmount(account.getPENALTY_FEE())));
                }
            }
            account.getBalance().decreaseAmount(thirdPartyDTO.getAmount());
        }

//        Customer refund
        if (thirdPartyDTO.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            account.getBalance().increaseAmount(thirdPartyDTO.getAmount().abs());
        }
        accountRepository.save(account);
        return account;
    }
}

