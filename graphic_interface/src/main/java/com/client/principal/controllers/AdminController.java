package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.Network.AdminUI;
import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.ClientDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    AdminUI adminUI;
    @Autowired
    UserUI userUI;

    @PostMapping("/updateCli")
    public String updateCli(
            @RequestParam("email") String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "subscriptionName", required = false) String subscriptionName) {

        if (name != null && name.isBlank())
            name = null;
        if (nickname != null && nickname.isBlank())
            nickname = null;
        if (password != null && password.isBlank())
            password = null;
        if (subscriptionName != null && subscriptionName.isBlank())
            subscriptionName = null;
        adminUI.updateCli(email, name, nickname, password, subscriptionName);
        return "redirect:/login";
    }

    @GetMapping("/GetAllClients")
    public List<ClientDAOEP> getAllCustomers() {
        return adminUI.getAllCustomers();
    }

    @PostMapping("/InsertAdmin")
    public String insertAdmin(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        adminUI.insertAdmin(name, nickname, email, password);
        return "redirect:/login";
    }

    @GetMapping("/InsertAdmin")
    public String showInsertAdminForm() {
        return "createAdmin";
    }

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        List<NewsEP> userNews = userUI.seeNewsOnLog(email);

        model.addAttribute("news", userNews);
        model.addAttribute("email", email);
        return "admin";
    }

    @GetMapping("/admin/news/create")
    public String mostrarFormularioNoticias() {
        return "insert-news";
    }

    @GetMapping("/admin/news/edit")
    public String mostrarFormularioModificaionNoticias() {
        return "update-new";
    }

    @GetMapping("/admin/user/edit")
    public String mostrarFormularioModificaionUsuario() {
        return "updateUsers";
    }

}
