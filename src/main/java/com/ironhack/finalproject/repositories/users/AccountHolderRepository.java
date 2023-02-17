package com.ironhack.finalproject.repositories.users;

import com.ironhack.finalproject.models.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Long> {
    Optional<AccountHolder> findById(Long id);
    Optional<AccountHolder> findByName(String name);

}
