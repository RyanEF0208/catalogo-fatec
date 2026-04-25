package br.com.fatec.catalogo.security;

import br.com.fatec.catalogo.Models.UsuarioModel;
import br.com.fatec.catalogo.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // libera página de login e cadastro
                        .requestMatchers("/usuarios/novo").hasRole("ADMIN")

                        // listar produtos pode todos
                        .requestMatchers("/produtos").permitAll()

                        // só ADMIN pode mexer
                        .requestMatchers("/produtos/novo", "/produtos/editar/**", "/produtos/excluir/**")
                        .hasRole("ADMIN")

                        // qualquer outra precisa estar logado
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/produtos", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                );

        return http.build();
    }
    @Bean
    CommandLineRunner init(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsByUsernameIgnoreCase("admin")) {
                UsuarioModel u = new UsuarioModel();
                u.setUsername("admin");
                u.setPassword(encoder.encode("12345")); // 🔥 AQUI CRIPTOGRAFA
                u.setRole("ROLE_ADMIN");
                repo.save(u);

                System.out.println("ADMIN CRIADO: admin / 12345");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}