package com.client.principal.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.client.principal.logic.ClientUC;
import com.client.principal.logic.Validations_Encriptations.Cesar;
import com.client.principal.logic.Validations_Encriptations.EmailPaswordVal;
import com.client.principal.logic.data.ClientUI;

@CrossOrigin(origins = "https://tuinfoinsta.netlify.app/")
@RestController
public class AdminController {
    @Autowired
    private ClientUC clientUC;
    @Autowired
    private Cesar cesar;

    @GetMapping("/InsertAdmin")
    public String insertAdmin(
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
                clientUC.createAdmin(name, nickname, email, password);
                return "Administrador creado correctamente: " + name + " " + nickname + " " + email;
            } else {
                return "Correo invalido: ";
            }
        } catch (DuplicateKeyException e) {
            return "Correo Electronico ya registrado: " + email;
        }
    }

    @GetMapping("/GetAdminByName")
    public ClientUI getAdminByName(@RequestParam("name") String name) {
        ClientUI Admin = clientUC.findClientByName(name);
        try {
            if (Admin != null && Admin.getRole()) {
                return Admin;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }

    @PostMapping("/updateCli")
    public boolean updateCli(@RequestBody Map<String, String> data) {
        try {
            String email = data.get("email");
            String name = data.get("name"); // puede ser null
            String nickname = data.get("nickname"); // puede ser null
            String password = data.get("password"); // puede ser null
            String subscriptionName = data.get("subscriptionName"); // opcional

            // Validaciones
            if (!EmailPaswordVal.isValidEmail(email)) {
                return false;
            }

            if (password != null && !password.isEmpty()) {
                if (!EmailPaswordVal.isValidPassword(password)) {
                    return false;
                }
                password = cesar.encrypt(password);
            }

            // Actualizar el cliente con la suscripción opcional
            clientUC.updateCli(name, nickname, email, password, subscriptionName);

            return true;

        } catch (DuplicateKeyException e) {
            return false;
        }
    }

}
