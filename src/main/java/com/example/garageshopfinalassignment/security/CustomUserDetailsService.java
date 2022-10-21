package com.example.garageshopfinalassignment.security;

import com.example.garageshopfinalassignment.models.User;
import com.example.garageshopfinalassignment.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public CustomUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findById(username);
        if (ou.isPresent()) {
            User user = ou.get();
            return new CustomUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
