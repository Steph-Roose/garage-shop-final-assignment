package com.example.garageshopfinalassignment.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User {
    @Id
    private String username;
    private String password;

// relationships
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

// getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

// setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
