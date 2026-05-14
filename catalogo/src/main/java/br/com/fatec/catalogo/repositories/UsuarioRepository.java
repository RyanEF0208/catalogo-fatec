package br.com.fatec.catalogo.repositories;

import br.com.fatec.catalogo.models.UsuarioModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    Optional<UsuarioModel> findByUsername(String nome);
}
