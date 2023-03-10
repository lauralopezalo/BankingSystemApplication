package com.ironhack.APIbank.repositories.users;

import com.ironhack.APIbank.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByName(String name);
    Optional<User> findByUsername(String username);

}
