package br.com.fatec.catalogo.Services;

import br.com.fatec.catalogo.Models.ProdutoModel;
import br.com.fatec.catalogo.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos(){
        return repository.findAll();
    }

    public List<ProdutoModel> listarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty() || nome == "%"){
            return repository.findAll();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public ProdutoModel buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado! " + id));
    }

    @Transactional
    public void salvar(ProdutoModel produto){


        produto.setNome(produto.getNome().trim().toLowerCase());

        ProdutoModel existente = repository.findByNomeIgnoreCase(produto.getNome());

        if (existente != null &&
                !existente.getIdProduto().equals(produto.getIdProduto())) {

            throw new IllegalArgumentException("Já existe um produto com esse nome!");
        }

        repository.save(produto);
    }
    @Transactional
    public void excluirID(Long id){

        repository.deleteById(id);
    }


}
