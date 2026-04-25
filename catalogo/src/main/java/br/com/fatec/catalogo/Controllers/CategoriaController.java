package br.com.fatec.catalogo.Controllers;

import br.com.fatec.catalogo.Models.CategoriaModel;
import br.com.fatec.catalogo.Services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/categorias/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        return "Cadastro-categorias";
    }

    @PostMapping("/categorias/novo")
    public String salvarCategoria(@Valid @ModelAttribute("categoria") CategoriaModel categoria,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            return "Cadastro-categorias";
        }

        try {
            service.salvar(categoria);
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao salvar categoria: " + e.getMessage());
            return "Cadastro-categorias";
        }

        return "redirect:/produtos";
    }
}