package br.com.fatec.catalogo.Services;

import br.com.fatec.catalogo.Models.CategoriaModel;
import br.com.fatec.catalogo.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaModel> listarTodas() {
        return repository.findAll();
    }

    public CategoriaModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
    }

    public void salvar(CategoriaModel categoria) {
        if(repository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new IllegalArgumentException("Esta categoria já existe!");
        }
        repository.save(categoria);
    }
}