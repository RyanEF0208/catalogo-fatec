package br.com.fatec.catalogo.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fatec.catalogo.Models.ProdutoModel;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    List<ProdutoModel> findByNomeContainingIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
    ProdutoModel findByNomeIgnoreCase(String nome);

}