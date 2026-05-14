package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioModel> listarTodos() {
        return repository.findAll();
    }

    public void salvar(UsuarioModel usuario) {
        if (usuario.getTipo() != null && !usuario.getTipo().startsWith("ROLE_")) {
            usuario.setTipo("ROLE_" + usuario.getTipo().toUpperCase());
        }
        repository.save(usuario);
    }

    public UsuarioModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
