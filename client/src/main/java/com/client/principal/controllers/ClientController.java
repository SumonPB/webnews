package com.client.principal.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.client.principal.logic.ClientUC;
import com.client.principal.logic.DAO.ClientDao;
import com.client.principal.logic.DAO.paymentDao;
import com.client.principal.logic.Validations_Encriptations.Cesar;
import com.client.principal.logic.Validations_Encriptations.EmailPaswordVal;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.ClientUI;
import com.client.principal.logic.data.network.NewsEP;
import com.client.principal.logic.data.network.PaymentEP;
import org.springframework.dao.DuplicateKeyException;
import com.client.principal.data.entities.Client;
import com.client.principal.data.repositorys.ClientRepository;

@CrossOrigin(origins = "https://tuinfoinsta.netlify.app/")
@RestController
public class ClientController {
    @Autowired
    private ClientUC clientUC;
    @Autowired
    private Cesar cesar;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/InsertClient")
    public String insertCustomer(@RequestBody Map<String, String> data) {
        String name = data.get("name");
        String nickname = data.get("nickname");
        String email = data.get("email");
        String password = data.get("password");

        try {
            if (EmailPaswordVal.isValidEmail(email)) {
                if (!EmailPaswordVal.isValidPassword(password)) {
                    return "Contraseña invalida, debe tener al menos 8 caracteres alfanumericos";
                }
                password = cesar.encrypt(password);
                clientUC.createClient(name, nickname, email, password);
                return "Usuario creado correctamente: ";
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

    @PostMapping("/updateClient")
    public boolean updateClient(@RequestBody Map<String, String> data) {
        try {
            String email = data.get("email");
            String name = data.get("name"); // puede ser null
            String nickname = data.get("nickname"); // puede ser null
            String password = data.get("password"); // puede ser null

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

            clientUC.updateClient(name, nickname, email, password);

            return true;

        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @GetMapping("/GetClientByEmail")
    public ClientUI GetClientByEmail(@RequestParam("email") String email) {
        return clientUC.findClientByEmail(email);
    }

    @PostMapping("/choseCategories")
    public ClientDao choseCategories(
            @RequestParam("email") String email,
            @RequestParam("categories") List<CategoryNews> categoryNews) {

        return clientUC.agregarCategorias(email, categoryNews);
    }

    @PostMapping("/buySubscription")
    public ClientDao buySubscription(
            @RequestParam("email") String email,
            @RequestParam("subscriptionName") String subscriptionName,
            @RequestParam("methodPay") String methodPay) {
        Client existingClient = clientRepository.getClientByEmail(email);
        if (existingClient == null) {
            throw new RuntimeException("Cliente no encontrado");
        }
        return clientUC.buySubscription(existingClient, subscriptionName, methodPay);
    }

    @GetMapping("/seeNewsNoLog")
    public List<NewsEP> seeNewsNoLog() {
        return clientUC.seeNews("");
    }

    @GetMapping("/seeNewsOnLog")
    public List<NewsEP> seeNewsOnLog(@RequestParam("email") String email) {

        return clientUC.seeNews(email);
    }

    @GetMapping("/seeAllBills")
    public List<paymentDao> seeAllBills(@RequestParam("email") String email) {

        return clientUC.getBills(email);
    }

}
