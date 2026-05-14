package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            UsuarioModel admin = new UsuarioModel();
            admin.setNome("admin");

            admin.setSenha(encoder.encode("admin"));
            admin.setTipo("ROLE_ADMIN");

            repository.save(admin);
            System.out.println("Usuario ADMIN adicionado com sucesso!");
        }
    }
}
