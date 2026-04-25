package br.com.fatec.catalogo.Controllers;

import br.com.fatec.catalogo.Models.UsuarioModel;
import br.com.fatec.catalogo.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios/novo")
    public String formUsuario(Model model){
        model.addAttribute("usuario", new UsuarioModel());
        return "Cadastro-usuario";
    }

    @PostMapping("/usuarios/novo")
    public String salvarUsuario(
            @Valid @ModelAttribute("usuario") UsuarioModel usuario,
            BindingResult result
    ){

        if (result.hasErrors()){
            return "Cadastro-usuario";
        }

        try {
            service.salvar(usuario);
        } catch (IllegalArgumentException e){
            result.rejectValue("username", "erro.username", e.getMessage());
            return "Cadastro-usuario";
        }

        return "redirect:/login";
    }
}