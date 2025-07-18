package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.CategoryNews;
import com.client.principal.logic.data.newtwork.ClientDAOEP;
import com.client.principal.logic.data.newtwork.UserEP;

@RestController
public class userController {
    @Autowired
    UserUI userUI;

    @GetMapping("/validate")
    public boolean validate(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return userUI.validation(email, password);
    }

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
            @RequestParam("password") String password) {
        return userUI.insertCustomer(name, nickname, email, password).toString();
    }

}
// listar usuarios con subscripcion activa
//