package com.example.garageshopfinalassignment.security;

import com.example.garageshopfinalassignment.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig  {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/roles").hasAuthority("ADMIN")
                .antMatchers("/users").hasAuthority("ADMIN")
                .antMatchers("users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/actions").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.GET, "/actions").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.GET, "/actions/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.PUT, "/actions/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.DELETE, "/actions/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.POST, "/cars").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.GET, "/cars/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.PATCH, "/cars/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.PUT, "/cars/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.DELETE, "/cars/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.GET, "/customers/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers("/logs").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.GET, "/logs/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.PATCH, "/logs/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.DELETE, "/logs/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.PUT, "/logs/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/parts").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.GET, "/parts").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.GET, "/parts/**").hasAnyAuthority("ADMIN", "MECH", "OFFICE")
                .antMatchers(HttpMethod.PUT, "/parts/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers(HttpMethod.DELETE, "/parts/**").hasAnyAuthority("ADMIN", "OFFICE")
                .antMatchers( "/customers", "/customers/**", "/invoices", "/invoices/**").hasAnyAuthority("ADMIN", "OFFICE")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
