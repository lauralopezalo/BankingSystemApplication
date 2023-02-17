package com.ironhack.finalproject.repositories.accounts;

import com.ironhack.finalproject.models.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {
    Optional<Savings>findById(Long id);

}
