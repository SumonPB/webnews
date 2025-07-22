package com.client.principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.client.principal.logic.Network.AdminUI;
import com.client.principal.logic.Network.UserUI;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        if (adminUI.validationAdmin(email, password)) {
            newSession.setAttribute("email", email);
            return "redirect:/admin";
        } else if (userUI.validation(email, password)) {
            newSession.setAttribute("email", email);
            return "redirect:/user";
        }

        model.addAttribute("error", "Credenciales inválidas");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/seeNewsNoLog";

    }

}
