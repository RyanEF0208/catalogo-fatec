package br.com.fatec.catalogo.Controllers;
import br.com.fatec.catalogo.Models.ProdutoModel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import br.com.fatec.catalogo.Services.*;
import br.com.fatec.catalogo.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;
import br.com.fatec.catalogo.Models.ProdutoModel;

@Controller
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produtos")
    public String listarProdutos(@RequestParam(required = false) String nome, Model model) {
        List<ProdutoModel> produtos;
        if (nome == null || nome.isEmpty()){
            produtos = service.listarTodos();
        }
        else{
            produtos = service.listarPorNome(nome);
        }

        model.addAttribute("produtos", produtos);
        model.addAttribute("busca", nome);
        return "Lista-produtos";
    }

    @GetMapping("/produtos/novo")
    public String ExibirFormulario(Model model){
        model.addAttribute("produto", new ProdutoModel());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "Cadastro-produtos";
    }

    @PostMapping("/produtos/novo")
    public String salvarProduto(@Valid @ModelAttribute("produto") ProdutoModel produto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "Cadastro-produtos";
        }

        try {
            service.salvar(produto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("nome", "erro.nome", e.getMessage());
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "Cadastro-produtos";
        }
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String exibirEdicao(@PathVariable("id") Long id, Model model) {
        ProdutoModel produto = service.buscarPorId(id);

        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriaService.listarTodas());

        return "Editar-produto";
    }

    @PostMapping("/produtos/editar/{id}")
    public String atualizarProduto(@PathVariable("id") Long id,
                                   @Valid @ModelAttribute("produto") ProdutoModel produto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "Editar-produto";
        }

        produto.setIdProduto(id);

        try {
            service.salvar(produto);
        } catch (IllegalArgumentException e) {
            result.rejectValue("nome", "erro.nome", e.getMessage());
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "Editar-produto";
        }

        return "redirect:/produtos";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable("id") Long id) {
        service.excluirID(id);
        return "redirect:/produtos";
    }


}
