package br.com.fatec.catalogo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UserDetailsService {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("aluno")
                .password("{noop}12345")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}12345")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
