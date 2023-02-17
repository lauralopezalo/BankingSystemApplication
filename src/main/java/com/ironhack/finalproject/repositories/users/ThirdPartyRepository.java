package com.ironhack.finalproject.repositories.users;

import com.ironhack.finalproject.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Long> {
}
