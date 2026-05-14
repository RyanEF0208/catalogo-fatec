package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos(String termoBusca){
        if (termoBusca != null && !termoBusca.isBlank()){
            return repository.findByNomeContainingIgnoreCaseOrCategoriaNomeContainingIgnoreCase(termoBusca, termoBusca);
        }
        return repository.findAll();
    }

    public ProdutoModel buscarPorId(long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    @Transactional
    public void salvar(ProdutoModel produto){
        boolean verificaNome = repository.existsByNome(produto.getNome());
        if (verificaNome && produto.getIdProduto() == 0){
            throw new IllegalArgumentException("Já existe um produto cadastrado com este nome.");
        }
        repository.save(produto);
    }

    @Transactional
    public void excluir(long id){
        repository.deleteById(id);
    }
}

