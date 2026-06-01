package br.com.fatec.catalogo.repositories;

import br.com.fatec.catalogo.models.AuditoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRepository extends JpaRepository<AuditoriaModel, Long> {
    List<AuditoriaModel>
    findAllByOrderByDataDesc();
}