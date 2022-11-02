package com.example.garageshopfinalassignment.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String rolename;

// relationships
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

// getters
    public String getRolename() {
        return rolename;
    }

// setters
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}