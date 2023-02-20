package com.ironhack.APIbank.repositories.accounts;

import com.ironhack.APIbank.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
