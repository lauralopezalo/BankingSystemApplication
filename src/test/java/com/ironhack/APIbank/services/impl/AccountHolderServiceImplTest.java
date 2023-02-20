package com.ironhack.APIbank.services.impl;

import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.embeddable.Address;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.Checking;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Role;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.accounts.CheckingRepository;
import com.ironhack.APIbank.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class AccountHolderServiceImplTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountHolderServiceImpl accountHolderServiceImpl;
    @Autowired
    CheckingRepository checkingRepository;

    private AccountHolder accountHolder1, accountHolder2;
    private Checking sourceAccount;
    private Checking targetAccount;

    @BeforeEach
    void setUp() {
        accountHolder1 = new AccountHolder("Teresa", "123", "username", LocalDate.of(1970,02,20),
                new Address("Baker Street", "08080", "ciudad","país"),
                new Address("avenue", "08880", "ciudad2","país2"));
        accountHolder1.setRoles(List.of(new Role("ACCOUNT_HOLDER")));
        accountHolderRepository.save(accountHolder1);

        accountHolder2 = new AccountHolder("Marisa", "456", "username", LocalDate.of(2005,8,30),
                new Address("Evergreen Terrace", "08080", "ciudad","país"),
                new Address("avenue", "08880", "ciudad2","país2"));
        accountHolder2.setRoles(List.of(new Role("ACCOUNT_HOLDER")));
        accountHolderRepository.save(accountHolder2);
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
    }



    @Test
    public void testGetAccountBalance() {
        Account account = new Checking();
        account.setPrimaryOwner(accountHolder1);
        account.setBalance(new Money(new BigDecimal("1000")));
        account.setId(1L);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AccountRepository accountHolderRepository = mock(AccountRepository.class);
        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(account));

        Money balance = account.getBalance();

        assertEquals(new Money(new BigDecimal("1000")), balance);
    }

    @Test
    @WithMockUser(username="Teresa")
    public void transfer_SuccessfulTransferFromCheckingAccount_ShouldUpdateBalances() {

        sourceAccount = new Checking(accountHolder1, null, "password");
        sourceAccount.setBalance(new Money(new BigDecimal("1000")));

        targetAccount = new Checking(accountHolder2, null, "password");
        targetAccount.setBalance(new Money(new BigDecimal("100")));

        checkingRepository.save(sourceAccount);
        checkingRepository.save(targetAccount);

        TransferenceDTO transferDTO = new TransferenceDTO(sourceAccount.getId(), targetAccount.getId(), new BigDecimal("500"));
        transferDTO.setTargetAccountOwner(accountHolder2.getName());
        accountHolderServiceImpl.transfer(transferDTO);

        Optional<Account> sourceAccountInRepo = accountRepository.findById(sourceAccount.getId());
        Optional<Account> targetAccountInRepo = accountRepository.findById(targetAccount.getId());
        assertEquals(new BigDecimal("500.00"), sourceAccountInRepo.get().getBalance().getAmount()); // $1000 - $12 (monthly maintenance fee) - $500 = $488
        assertEquals(new BigDecimal("600.00"), targetAccountInRepo.get().getBalance().getAmount()); // $500 + $500 = $1000
    }
}