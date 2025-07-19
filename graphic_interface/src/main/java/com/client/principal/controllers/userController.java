package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.CategoryNews;
import com.client.principal.logic.data.newtwork.ClientDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;
import com.client.principal.logic.data.newtwork.UserEP;

import jakarta.servlet.http.HttpSession;

@Controller
public class userController {
    @Autowired
    UserUI userUI;

    @GetMapping("/buySubscription")
    public UserEP buySubscription(
            @RequestParam("email") String email,
            @RequestParam("subscriptionName") String subscriptionName,
            @RequestParam("methodPay") String methodPay) {

        return userUI.buySubscription(email, subscriptionName, methodPay);
    }

    @GetMapping("/updateClient")
    public String updateClient(
            @RequestParam("email") String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "password", required = false) String password) {
        userUI.updateClient(email, name, nickname, password);
        return "Modificado con exito";
    }

    @GetMapping("/choseCategories")
    public ClientDAOEP choseCategories(
            @RequestParam("email") String email,
            @RequestParam("categories") List<CategoryNews> categoryNews) {
        return userUI.choseCategories(email, categoryNews);
    };

    @GetMapping("/InsertClient")
    public String insertCustomer(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        userUI.insertCustomer(name, nickname, email, password);
        model.addAttribute("success", true);
        return "register_success";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Thymeleaf buscará "classpath:/templates/register.html"
    }

    @GetMapping("/user")
    public String userPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            return "redirect:/login";
        }

        List<NewsEP> userNews = userUI.seeNewsOnLog(email);
        model.addAttribute("news", userNews);
        model.addAttribute("email", email);

        return "user";
    }
}
// listar usuarios con subscripcion activa
//