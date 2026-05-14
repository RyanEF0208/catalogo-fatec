package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public String listarCategoria(Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "listar-categoria";
    }

    @GetMapping("/novo")
    public String formCategoria(Model model) {
        model.addAttribute("categoriaModel", new CategoriaModel());
        return "cadastro-categoria";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@Valid CategoriaModel categoriaModel, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro-categoria";
        }
        repository.save(categoriaModel);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") long id, Model model) {
        CategoriaModel cat = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido"));
        model.addAttribute("categoriaModel", cat);
        return "cadastro-categoria";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/categorias";
    }
}