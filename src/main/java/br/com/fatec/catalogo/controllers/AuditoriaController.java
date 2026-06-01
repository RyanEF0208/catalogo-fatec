package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.services.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService service;

    @GetMapping
    public String historico(Model model){

        model.addAttribute(
                "auditorias",
                service.listar()
        );

        return "historico-produtos";
    }

}
