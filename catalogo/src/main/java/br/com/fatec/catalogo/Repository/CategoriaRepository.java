package br.com.fatec.catalogo.Repository;

import br.com.fatec.catalogo.Models.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}