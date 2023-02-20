package com.ironhack.APIbank.services.impl;

import com.ironhack.APIbank.controllers.dto.users.ThirdPartyDTO;
import com.ironhack.APIbank.embeddable.Address;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.accounts.Checking;
import com.ironhack.APIbank.models.users.AccountHolder;
import com.ironhack.APIbank.models.users.Role;
import com.ironhack.APIbank.models.users.ThirdParty;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.users.AccountHolderRepository;
import com.ironhack.APIbank.repositories.users.ThirdPartyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ThirdPartyServiceImplTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    ThirdPartyServiceImpl thirdPartyServiceImpl;
    AccountHolder accountHolder;

    @BeforeEach
    void setUp() {
        accountHolder = new AccountHolder("Teresa", "123", "username", LocalDate.of(1970,2,20),
                new Address("Baker Street", "08080", "ciudad","país"),
                new Address("avenue", "08880", "ciudad2","país2"));
        accountHolder.setRoles(List.of(new Role("ACCOUNT_HOLDER")));
        accountHolderRepository.save(accountHolder);
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
    }


    @Test
    public void transferMoney_SuccessfulTransfer_ShouldUpdateBalances() {
        ThirdParty thirdParty = new ThirdParty("dataphone", "hash", "password","dataphone");
        Role role = new Role("THIRD_PARTY");
        thirdParty.setRoles(List.of(role));
        thirdPartyRepository.save(thirdParty);

        Account sourceAccount = new Checking(accountHolder, null, "password");
        sourceAccount.setBalance(new Money(new BigDecimal("1000")));
        accountRepository.save(sourceAccount);

        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO(thirdParty.getId(), new BigDecimal("-500"), sourceAccount.getId(), sourceAccount.getPrimaryOwner().getPassword());
        thirdPartyServiceImpl.transferMoney("hashed_key", thirdPartyDTO);
        Optional<Account> sourceAccountInRepo = accountRepository.findById(sourceAccount.getId());
        assertEquals(new BigDecimal("1500.00"),sourceAccountInRepo.get().getBalance().getAmount());
    }
}