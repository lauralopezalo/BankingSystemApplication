package com.ironhack.APIbank.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.APIbank.embeddable.Address;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.Checking;
import com.ironhack.APIbank.models.accounts.Savings;
import com.ironhack.APIbank.models.accounts.StudentChecking;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Role;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.accounts.CheckingRepository;
import com.ironhack.APIbank.repositories.accounts.SavingsRepository;
import com.ironhack.APIbank.repositories.accounts.StudentCheckingRepository;
import com.ironhack.APIbank.repositories.users.AccountHolderRepository;
import com.ironhack.APIbank.repositories.users.AdminRepository;
import com.ironhack.APIbank.repositories.users.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class AccountHolderServiceImplTest {

    @Autowired
    private AccountHolderServiceImpl accountHolderService;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    private MockMvc mockMvc;

    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;

    private AccountHolder accountHolder1, accountHolder2;
    private Savings savings;
    private Checking checking;
    private StudentChecking studentChecking;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

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
    void transfer() {
    }
}