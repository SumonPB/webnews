package com.client.principal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaController {

    @GetMapping("/hola")
    public String hola(Model model) {
        model.addAttribute("mensaje", "¡Hola Mundo desde Thymeleaf!");
        return "hola"; // Busca hola.html en templates
    }
}
