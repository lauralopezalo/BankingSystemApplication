package com.ironhack.finalproject.services.impl;

import com.ironhack.finalproject.classes.Money;
import com.ironhack.finalproject.controllers.dto.accounts.CreditCardDTO;
import com.ironhack.finalproject.controllers.dto.accounts.SavingsDTO;
import com.ironhack.finalproject.models.accounts.*;
import com.ironhack.finalproject.models.users.*;
import com.ironhack.finalproject.controllers.dto.accounts.CheckingDTO;
import com.ironhack.finalproject.controllers.dto.accounts.BalanceDTO;
import com.ironhack.finalproject.repositories.accounts.*;
import com.ironhack.finalproject.repositories.users.AccountHolderRepository;
import com.ironhack.finalproject.repositories.users.AdminRepository;
import com.ironhack.finalproject.repositories.users.ThirdPartyRepository;
import com.ironhack.finalproject.repositories.users.UserRepository;
import com.ironhack.finalproject.services.interfaces.AdminServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceInt {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    UserRepository userRepository;

//   /*public Admin addNewAdmin(AdminDTO adminDTO) {
//        Admin admin = new Admin(adminDTO.getName(), adminDTO.getPassword());
//        //admin.setName(adminDTO.getName());
//        //admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
//        Role adminRole = new Role("ADMIN");
//        admin.setRoles(List.of(adminRole));
//        adminRepository.save(admin);
//        return admin;
//    }*/

    public Admin addAdmin(Admin admin) {
        Role adminRole = new Role("ADMIN");
        admin.setRoles(List.of(adminRole));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        Role accountHolderRole = new Role("ACCOUNT_HOLDER");
        accountHolder.setRoles(List.of(accountHolderRole));
        accountHolder.setPassword(passwordEncoder.encode(accountHolder.getPassword()));
        return accountHolderRepository.save(accountHolder);
    }

    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        Role thirdPartyRole = new Role("THIRD_PARTY");
        thirdParty.setRoles(List.of(thirdPartyRole));
        return thirdPartyRepository.save(thirdParty);
    }

    public Account addChecking(CheckingDTO checkingDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(checkingDTO.getPrimaryOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        LocalDate birthdate = primaryOwner.getBirthDate();
        int age = (birthdate.until(LocalDate.now())).getYears();
        AccountHolder secondaryOwner = null;
        if (checkingDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(checkingDTO.getSecondaryOwnerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        }
        Account newAccount;
        if (age < 24) {
            newAccount = studentCheckingRepository.save(new StudentChecking(primaryOwner, secondaryOwner, checkingDTO.getSecretKey()));
        } else {
            newAccount = checkingRepository.save(new Checking(primaryOwner, secondaryOwner, checkingDTO.getSecretKey()));
        }
        return newAccount;
    }

    public Savings addSavings(SavingsDTO savingsDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(savingsDTO.getPrimaryOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        AccountHolder secondaryOwner = null;
        if (savingsDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(savingsDTO.getSecondaryOwnerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        }
        Savings savings = new Savings(primaryOwner, secondaryOwner, savingsDTO.getSecretKey());

        return savingsRepository.save(savings);
    }

    public CreditCard addCreditCardAccount(CreditCardDTO creditCardDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(creditCardDTO.getPrimaryOwnerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        AccountHolder secondaryOwner = null;
        if (creditCardDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(creditCardDTO.getSecondaryOwnerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account holder not found"));
        }
        CreditCard creditCard = new CreditCard(primaryOwner, secondaryOwner);
        if (creditCardDTO.getCreditLimit() != null) {
            creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
        }
        if (creditCardDTO.getInterestRate() != null) {
            creditCard.setInterestRate(creditCardDTO.getInterestRate());
        }
        return creditCardRepository.save(creditCard);
    }

    public Account getAccount(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(account instanceof Savings){
            savingsRepository.findById(accountId).get().addInterest();
        }
        else if(account instanceof CreditCard ){
            creditCardRepository.findById(accountId).get().addInterest();
        }
        return account;
    }


    public Money getAccountBalance(Long accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(account instanceof Savings){
            savingsRepository.findById(accountId).get().addInterest();
        }
        else if(account instanceof CreditCard ){
            creditCardRepository.findById(accountId).get().addInterest();
        }
        return account.getBalance();
    }

    public Account updateAccountBalance(Long accountId, Money money) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(account instanceof Savings){
            savingsRepository.findById(accountId).get().addInterest();
        }
        else if(account instanceof CreditCard ){
            creditCardRepository.findById(accountId).get().addInterest();
        }
        account.setBalance(money);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
                accountRepository.delete(account);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(user);
    }

}

