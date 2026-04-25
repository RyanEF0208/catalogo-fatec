package br.com.fatec.catalogo.Repository;

import br.com.fatec.catalogo.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);
}
