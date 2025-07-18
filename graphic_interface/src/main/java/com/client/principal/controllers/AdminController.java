package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.Network.AdminUI;
import com.client.principal.logic.data.newtwork.ClientDAOEP;

@RestController
public class AdminController {
    @Autowired
    AdminUI adminUI;

    @GetMapping("/updateCli")
    public String updateCli(
            @RequestParam("email") String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "subscriptionName", required = false) String subscriptionName) {
        return adminUI.updateCli(email, name, nickname, password, subscriptionName);
    }

    @GetMapping("/validateAdmin")
    public Boolean validationAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return adminUI.validationAdmin(email, password);
    }

    @GetMapping("/GetAllClients")
    public List<ClientDAOEP> getAllCustomers() {
        return adminUI.getAllCustomers();
    }

    @GetMapping("/InsertAdmin")
    public String insertAdmin(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return adminUI.insertAdmin(name, nickname, email, password);
    }

}
