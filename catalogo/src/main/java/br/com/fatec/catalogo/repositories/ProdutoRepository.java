package br.com.fatec.catalogo.repositories;
import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.ProdutoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
  List<ProdutoModel> findByNomeContainingIgnoreCaseOrCategoriaNomeContainingIgnoreCase(String nome, String categoriaNome);

  boolean existsByNome(String nome);
}
