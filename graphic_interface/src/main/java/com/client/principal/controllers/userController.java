package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.client.principal.logic.Network.PaymentUI;
import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.CategoryNews;
import com.client.principal.logic.data.newtwork.ClientDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;
import com.client.principal.logic.data.newtwork.PaymentEP;
import com.client.principal.logic.data.newtwork.UserEP;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("email")
public class userController {
    @Autowired
    UserUI userUI;
    @Autowired
    PaymentUI paymentUI;

    @PostMapping("/buySubscription")
    public String buySubscription(
            @RequestParam("subscriptionName") String subscriptionName,
            @RequestParam("methodPay") String methodPay,
            HttpSession session,
            Model model) {

        String email = (String) session.getAttribute("email");
        UserEP result = userUI.buySubscription(email, subscriptionName, methodPay);

        model.addAttribute("message", "Subscripción comprada con éxito");
        return "buySubscription";
    }

    @PostMapping("/updateClient")
    public String updateClient(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        String email = (String) session.getAttribute("email");

        if (email == null) {
            return "redirect:/login";
        }

        if (password != null && !password.isBlank()) {
            userUI.updateClient(email, name, nickname, password);
        } else {
            userUI.updateClient(email, name, nickname, null);
        }

        model.addAttribute("message", "Modificado con éxito");
        return "redirect:/user";
    }

    @PostMapping("/choseCategories")
    public String choseCategories(
            HttpSession session,
            @RequestParam("categories") List<CategoryNews> categoryNews,
            Model model) {
        String email = (String) session.getAttribute("email");
        userUI.choseCategories(email, categoryNews);
        model.addAttribute("message", "Categorías seleccionadas correctamente.");
        return "redirect:/user";
    }

    @GetMapping("/chooseCategoriesPage")
    public String chooseCategoriesPage(Model model) {
        List<CategoryNews> categories = List.of(
                CategoryNews.TECNOLOGIA,
                CategoryNews.POLITICA,
                CategoryNews.ENTRETENIMIENTO,
                CategoryNews.SALUD);
        model.addAttribute("categories", categories);
        return "choose_categories";
    }

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
    public String showRegisterForm(HttpSession session) {
        return "register";
    }

    @GetMapping("/user")
    public String userPage(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }
        List<NewsEP> userNews = userUI.seeNewsOnLog(email);

        List<String> bills = userUI.getClientByEmail(email).getBillsId();
        if (bills != null && !bills.isEmpty()) {
            String lastBillId = bills.get(bills.size() - 1);
            PaymentEP bill = paymentUI.getBillById(lastBillId);
            model.addAttribute("subscription", bill);
        } else {
            model.addAttribute("subscription", null);
        }
        model.addAttribute("news", userNews);
        model.addAttribute("email", email);
        return "user";
    }

    @GetMapping("/configureProfile")
    public String configureProfile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            return "redirect:/login";
        }

        UserEP client = userUI.getClientByEmail(email);
        model.addAttribute("client", client);
        return "configure_profile";
    }

    @GetMapping("/goToBuySubscription")
    public String goToBuySubscription() {
        return "buySubscription";
    }

}
// listar usuarios con subscripcion activa
//