package com.hcmute.management.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final CustomEmployeeDetailsService customEmployeeDetailsService;
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/**").authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/")
                .and().logout().logoutUrl("/logout");
       return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Pwd");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        System.out.println("Auth manager");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customEmployeeDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
