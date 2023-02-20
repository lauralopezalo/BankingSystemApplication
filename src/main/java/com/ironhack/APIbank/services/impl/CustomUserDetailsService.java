package com.ironhack.APIbank.services.impl;

import com.ironhack.APIbank.models.users.Role;
import com.ironhack.APIbank.models.users.User;
import com.ironhack.APIbank.repositories.accounts.AccountRepository;
import com.ironhack.APIbank.repositories.users.AccountHolderRepository;
import com.ironhack.APIbank.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByName(name);
        // Check if user is present
        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        // Create a list of authorities (roles)
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for(Role role : optionalUser.get().getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }

        // Return a Spring Security User with my User username, password and roles
        return new org.springframework.security.core.userdetails.User(
                optionalUser.get().getName(),
                optionalUser.get().getPassword(),
                authorities);
    }


}