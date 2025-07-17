package com.client.principal.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.client.principal.logic.ClientUC;
import com.client.principal.logic.DAO.ClientDao;
import com.client.principal.logic.NETWORK.GetSubscription;
import com.client.principal.logic.Validations_Encriptations.Cesar;
import com.client.principal.logic.Validations_Encriptations.EmailPaswordVal;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.ClientUI;
import com.client.principal.logic.data.network.SubscriptionEP;
import com.client.principal.logic.data.network.subscriptionTypes;

import org.springframework.dao.DuplicateKeyException;

@RestController
public class ClientController {
    @Autowired
    private ClientUC clientUC;
    @Autowired
    private Cesar cesar;
    @Autowired
    private GetSubscription getSubscription;

    @GetMapping("/InsertClient")
    public String insertCustomer(
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            if (EmailPaswordVal.isValidEmail(email)) {
                if (!EmailPaswordVal.isValidPassword(password)) {
                    return "Contraseña invalida, debe tener al menos 8 caracteres alfanumericos";
                }
                password = cesar.encrypt(password);
                clientUC.createClient(name, nickname, email, password);
                return "Cliente creado correctamente: " + name + " " + nickname + " " + email;
            } else {
                return "Correo invalido: ";
            }
        } catch (DuplicateKeyException e) {
            return "Correo Electronico ya registrado: " + email;
        }

    }

    @GetMapping("/GetAllClients")
    public List<ClientDao> getAllCustomers() {
        return clientUC.getAllClients();
    }

    @GetMapping("/GetClientByName")
    public ClientUI getClientByName(@RequestParam("name") String name) {
        ClientUI client = clientUC.findClientByName(name);
        return client;
    }

    @GetMapping("/updateClient")
    public String updateClient(
            @RequestParam("email") String email,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "password", required = false) String password) {
        try {
            if (password != null) {
                if (!EmailPaswordVal.isValidPassword(password)) {
                    return "Contraseña inválida, debe tener al menos 8 caracteres alfanuméricos.";
                }
            }
            if (!EmailPaswordVal.isValidEmail(email)) {
                return "Correo inválido.";
            }

            password = cesar.encrypt(password);

            clientUC.updateClient(name, nickname, email, password);

            return "Cliente actualizado correctamente: " + name + " " + nickname + " " + email;

        } catch (DuplicateKeyException e) {
            return "Correo electrónico ya registrado: " + email;
        }
    }

    @GetMapping("/GetClientByEmail")
    public ClientUI GetClientByEmail(@RequestParam("email") String email) {
        return clientUC.findClientByEmail(email);
    }

    @GetMapping("/choseCategories")
    public ClientDao choseCategories(
            @RequestParam("email") String email,
            @RequestParam("categories") List<CategoryNews> categoryNews) {

        return clientUC.agregarCategorias(email, categoryNews);
    }

    @GetMapping("/buySubscription")
    public ClientDao buySubscription(
            @RequestParam("email") String email,
            @RequestParam("subscriptionName") String subscriptionName,
            @RequestParam("methodPay") String methodPay) {

        return clientUC.buySubscription(email, subscriptionName, methodPay);
    }

}
