package br.com.fatec.catalogo.Services;


import br.com.fatec.catalogo.Models.UsuarioModel;
import br.com.fatec.catalogo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvar(UsuarioModel usuario){

        usuario.setUsername(usuario.getUsername().trim().toLowerCase());

        if (repository.existsByUsernameIgnoreCase(usuario.getUsername())) {
            throw new IllegalArgumentException("Usuário já existe!");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (!usuario.getRole().startsWith("ROLE_")) {
            usuario.setRole("ROLE_" + usuario.getRole());
        }

        repository.save(usuario);
    }
}