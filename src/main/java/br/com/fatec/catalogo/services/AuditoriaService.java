package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.AuditoriaModel;
import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.AuditoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    public void registrar(String acao, ProdutoModel produto){

        AuditoriaModel auditoria = new AuditoriaModel();

        auditoria.setUsuario(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        );

        auditoria.setIdProduto(produto.getIdProduto());

        auditoria.setAcao(acao);

        auditoria.setProduto(produto.getNome());

        auditoria.setQuantidade(produto.getQuantidade());

        auditoria.setEstoqueBaixo(
                produto.getQuantidade() < 5
        );

        auditoria.setData(LocalDateTime.now());

        repository.save(auditoria);
    }

    public List<AuditoriaModel> listar(){
        return repository.findAllByOrderByDataDesc();
    }
}