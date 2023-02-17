package com.ironhack.finalproject.models.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hashedKey;
    @JsonIgnore
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Role> roles;

    public ThirdParty() {
    }

    public ThirdParty(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}