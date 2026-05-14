package br.com.fatec.catalogo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/categorias/**").hasRole("ADMIN")
                        .requestMatchers("/produtos").permitAll() // Público
                        .requestMatchers("/produtos/novo", "/produtos/editar/**", "/produtos/excluir/**").hasRole("ADMIN") // Admins
                        .anyRequest().authenticated() // Restante protegido
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/produtos", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutSuccessUrl("/produtos"));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        // user comum
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}123456")
                .roles("ADMIN") // herança de roles
                .build();

        UserDetails user = User.builder()
                .username("aluno")
                .password("{noop}123456") // noop = sem cripto
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}


