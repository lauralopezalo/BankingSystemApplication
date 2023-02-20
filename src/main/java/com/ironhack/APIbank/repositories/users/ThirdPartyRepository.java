package com.ironhack.APIbank.repositories.users;

import com.ironhack.APIbank.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Long> {
}
