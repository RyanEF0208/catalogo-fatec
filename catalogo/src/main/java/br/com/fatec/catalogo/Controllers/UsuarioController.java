package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", service.listarTodos());
        return "listar-usuarios";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "cadastro-usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid UsuarioModel usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro-usuario";
        }
        service.salvar(usuario);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        model.addAttribute("usuarioModel", service.buscarPorId(id));
        return "cadastro-usuario";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") long id) {
        service.excluir(id);
        return "redirect:/usuarios";
    }
}
