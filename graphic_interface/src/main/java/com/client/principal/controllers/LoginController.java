package com.client.principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.client.principal.logic.Network.AdminUI;
import com.client.principal.logic.Network.UserUI;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    AdminUI adminUI;
    @Autowired
    UserUI userUI;

    @PostMapping("/LoginCheck")
    public String loginCheck(@RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (adminUI.validationAdmin(email, password)) {
            session.setAttribute("email", email);
            return "redirect:/admin";
        } else if (userUI.validation(email, password)) {
            session.setAttribute("email", email);
            return "redirect:/user";
        }

        model.addAttribute("error", "Credenciales inválidas");
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

}
