package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.CategoriaService;
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public String listar(@RequestParam(value = "nome", required = false) String nome,
                         @RequestParam(value = "categoriaId", required = false) Long categoriaId,
                         Model model) {

        // 1. Lógica de Filtragem (Produtos)
        if (nome != null && !nome.isBlank()) {
            model.addAttribute("produtos", service.listarPorNome(nome));
        } else if (categoriaId != null) {
            model.addAttribute("produtos", service.listarPorCategoria(categoriaId));
        } else {
            model.addAttribute("produtos", service.listarTodos());
        }

        // 2. O QUE ESTAVA FALTANDO: Carregar as categorias para o <select> do filtro
        // Sem isso, o th:each="cat : ${categorias}" no HTML não encontra nada
        model.addAttribute("categorias", categoriaService.listarTodas());

        return "lista-produtos";
    }

    @GetMapping("/historico")
    public String historico(Model model) {

        model.addAttribute("produtos",
                service.listarHistorico());

        return "historico-produtos";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        model.addAttribute("categorias", categoriaService.listarTodas()); // Garante que o select funcione
        return "cadastro-produto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attributes) {

        if (result.hasErrors()) {

            model.addAttribute("categorias",
                    categoriaService.listarTodas());

            return "cadastro-produto";
        }

        try {

            service.salvar(produto);

        } catch (RuntimeException e) {

            model.addAttribute("categorias",
                    categoriaService.listarTodas());

            model.addAttribute("erro",
                    e.getMessage());

            return "cadastro-produto";
        }

        attributes.addFlashAttribute(
                "sucesso",
                "Produto salvo com sucesso às "
                        + LocalTime.now().withNano(0)
        );

        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model) {
        model.addAttribute("produto", service.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "cadastro-produto"; // Reutilizamos o mesmo form para editar
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }

    @Autowired
    private CategoriaService categoriaService;


}