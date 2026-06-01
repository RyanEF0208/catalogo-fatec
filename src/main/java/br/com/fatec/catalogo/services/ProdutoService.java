package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private AuditoriaService auditoriaService;

    public List<ProdutoModel> listarTodos() {

        return repository.findAll();
    }
    // Resolve o Desafio 1
    public List<ProdutoModel> listarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public boolean estoqueBaixo(ProdutoModel p){
        return p.getQuantidade() < 5;
    }

    public ProdutoModel buscarPorId(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    public List<ProdutoModel> listarPorCategoria(Long idCategoria) {
        return repository.findByCategoriaIdCategoria(idCategoria);
    }

    public List<ProdutoModel> listarHistorico() {
        return repository.findAllByOrderByDataCadastroDesc();
    }

    // Resolve o Desafio 2
    @Transactional
    public void salvar(ProdutoModel produto) {

        boolean novo = produto.getIdProduto() == 0;

        if (produto.getQuantidade() < 0) {
            throw new RuntimeException("A quantidade não pode ser negativa.");
        }

        if (produto.getIdProduto() == 0 &&
                repository.existsByNome(produto.getNome())) {

            throw new RuntimeException("Já existe um produto com este nome.");
        }

        if (produto.getIdProduto() == 0) {
            produto.setDataCadastro(LocalDateTime.now());
        }

        repository.save(produto);

        auditoriaService.registrar(novo ? "CREATE" : "UPDATE", produto);
    }

    @Transactional
    public void excluir(long id) {
        ProdutoModel produto = buscarPorId(id);

        repository.deleteById(id);

        auditoriaService.registrar("DELETE", produto);
    }
}