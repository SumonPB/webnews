package com.client.principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.ClientUC;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ValidationController {
    @Autowired
    private ClientUC clientUC;

    @GetMapping("/validate")
    public Boolean validation(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return clientUC.validateClient(email, password);
    }

    @GetMapping("/validateAdmin")
    public Boolean validationAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        return clientUC.validateAdmin(email, password);
    }

}
