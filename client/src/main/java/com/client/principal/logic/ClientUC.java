package com.client.principal.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Client;
import com.client.principal.data.repositorys.ClientRepository;
import com.client.principal.logic.DAO.ClientDao;
import com.client.principal.logic.DAO.paymentDao;
import com.client.principal.logic.DTO.ClientDTO;
import com.client.principal.logic.DTO.PaymentDTO;
import com.client.principal.logic.NETWORK.CreateBill;
import com.client.principal.logic.NETWORK.EmailService;
import com.client.principal.logic.NETWORK.GetSubscription;
import com.client.principal.logic.NETWORK.ViewNews;
import com.client.principal.logic.Validations_Encriptations.Cesar;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.ClientUI;
import com.client.principal.logic.data.network.NewsEP;
import com.client.principal.logic.data.network.PaymentEP;
import com.client.principal.logic.data.network.SubscriptionEP;
import com.client.principal.logic.data.network.subscriptionTypes;

@Service
public class ClientUC {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Cesar cesar;
    @Autowired
    private GetSubscription getSubscription;
    @Autowired
    private CreateBill createBill;
    @Autowired
    private ViewNews viewNews;
    @Autowired
    private EmailService emailService;

    public ClientUI createAdmin(String name,
            String nickname,
            String email,
            String password) {

        ClientUI client = ClientUI.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(true)
                .subscriptionID(getSubscription.GetSubscriptionByName("INSIDER").getId())
                .build();
        clientRepository.save(ClientDTO.toClient(client));
        return client;
    }

    public ClientUI createClient(String name,
            String nickname,
            String email,
            String password) {
        ClientUI client = ClientUI.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(false)
                .subscriptionID(getSubscription.GetSubscriptionByName("FREE").getId())
                .build();

        clientRepository.save(ClientDTO.toClient(client));
        return client;
    }

    public List<ClientDao> getAllClients() {
        List<ClientDao> clients = new ArrayList<>();
        clientRepository.findAll().forEach(client -> {
            clients.add(ClientDTO.toClientDao(client));
        });
        return clients;
    }

    public ClientUI findClientByEmail(String email) {
        Client client = clientRepository.getClientByEmail(email);
        if (client == null) {
            return null;
        }
        return ClientDTO.toClientUI(client);
    }

    public ClientUI findClientByName(String name) {
        Client client = clientRepository.getClientByName(name);
        if (client == null) {
            return null;
        }
        return ClientDTO.toClientUI(client);
    }

    public Boolean validateClient(String email, String password) {
        Client clientBD = clientRepository.getClientByEmail(email);

        if (clientBD == null) {
            return false;
        } else {
            if (clientBD.getBillsId() != null) {
                PaymentEP currentbill = createBill.getBillById(clientBD.getBillsId().getLast());
                LocalDateTime now = LocalDateTime.now();
                if (currentbill.getEndSubscription().isBefore(now)) {
                    clientBD.setSubscriptionID(getSubscription.GetSubscriptionByName("FREE").getId());
                    clientBD.setCategory(null);
                    clientRepository.save(clientBD);
                }
            }
        }
        return cesar.decrypt(clientBD.getPassword()).equals(password);
    }

    public Boolean validateAdmin(String email, String password) {
        Client clientBD = clientRepository.getClientByEmail(email);

        if (clientBD == null) {
            return false;
        }
        if (!clientBD.getRole()) {
            return false;
        }
        return cesar.decrypt(clientBD.getPassword()).equals(password);
    }

    public ClientUI updateClient(String name,
            String nickname,
            String email,
            String password) {
        Client existingClient = clientRepository.getClientByEmail(email);
        if (existingClient == null) {
            return null; // Client not found
        }
        if (name != null)
            existingClient.setName(name);
        if (nickname != null)
            existingClient.setNickname(nickname);
        if (password != null)
            existingClient.setPassword(password);

        return ClientDTO.toClientUI(clientRepository.save(existingClient));
    }

    public ClientDao agregarCategorias(String email, List<CategoryNews> categoryNews) {

        Client existingClient = clientRepository.getClientByEmail(email);

        SubscriptionEP subscription = getSubscription.findSubscriptionById(existingClient.getSubscriptionID());

        if (subscription.getName().equals(subscriptionTypes.FREE)) {
            return ClientDTO.toClientDao(existingClient);
        } else if (subscription.getName().equals(subscriptionTypes.TITULAR)) {
            if (categoryNews.size() >= 1 && categoryNews.size() <= 2) {
                if (categoryNews.size() == 1) {
                    existingClient.setCategory(Arrays.asList(categoryNews.get(0)));
                } else {
                    existingClient.setCategory(Arrays.asList(categoryNews.get(0), categoryNews.get(1)));
                }
            } else {
                existingClient.setCategory(Arrays.asList(categoryNews.get(0), categoryNews.get(1)));
            }

            return ClientDTO.toClientDao(clientRepository.save(existingClient));
        } else if (subscription.getName().equals(subscriptionTypes.REDACCIONPlus)
                || subscription.getName().equals(subscriptionTypes.INSIDER)) {
            existingClient.setCategory(categoryNews);
            return ClientDTO.toClientDao(clientRepository.save(existingClient));
        }

        throw new IllegalStateException("Tipo de suscripción desconocido: " + subscription.getName());
    }

    public ClientDao buySubscription(Client existingClient, String subscriptionName, String payMethod) {
        PaymentEP newBill = createBill.createPayment(existingClient.getEmail(), subscriptionName, payMethod);
        if (existingClient.getBillsId() == null) {
            existingClient.setBillsId(new ArrayList<>());
        }
        existingClient.getBillsId().add(newBill.getId());
        existingClient.setSubscriptionID(getSubscription.GetSubscriptionByName(subscriptionName).getId());
        emailService.sendSubscriptionEmail(existingClient.getEmail(), newBill);
        return ClientDTO.toClientDao(clientRepository.save(existingClient));

    }
    // ----------------------------------------------------------------------------------------------------------

    public ClientUI updateCli(String name,
            String nickname,
            String email,
            String password,
            String subscriptionName) {

        Client existingClient = clientRepository.getClientByEmail(email);
        if (existingClient == null) {
            return null;
        }

        if (name != null && !name.equalsIgnoreCase("null")) {
            existingClient.setName(name);
        }
        if (nickname != null && !nickname.equalsIgnoreCase("null")) {
            existingClient.setNickname(nickname);
        }
        if (password != null && !password.equalsIgnoreCase("null")) {
            existingClient.setPassword(password);
        }
        if (subscriptionName != null && !subscriptionName.equalsIgnoreCase("null")) {
            if (subscriptionName.equals("FREE")) {
                existingClient.setCategory(null);
            }

            var subscription = getSubscription.GetSubscriptionByName(subscriptionName);
            if (subscription != null) {
                existingClient.setSubscriptionID(subscription.getId());
                buySubscription(existingClient, subscriptionName, "AdminUpdate");
            } else {
                throw new IllegalArgumentException("Suscripción no válida: " + subscriptionName);
            }
        }

        return ClientDTO.toClientUI(clientRepository.save(existingClient));
    }

    // ----------------------------------------------------------------------------------------------------------
    public List<NewsEP> seeNews(String email) {
        if (!email.isEmpty()) {
            ClientUI client = findClientByEmail(email);
            SubscriptionEP ns = getSubscription.findSubscriptionById(client.getSubscriptionID());
            return viewNews.GetNewsByClient(ns.getName().toString(), client.getCategory());
        } else {
            return viewNews.GetNewsByClient("FREE", null);
        }

    }

    public List<paymentDao> getBills(String email) {
        List<PaymentEP> paymentEPs = new ArrayList<>();
        if (!email.isEmpty()) {
            ClientUI clientUI = findClientByEmail(email);
            if (clientUI.getBillsId() != null) {
                for (String billId : clientUI.getBillsId()) {
                    paymentEPs.add(createBill.getBillById(billId));
                }
            }
        }
        return PaymentDTO.toPaymentDao(paymentEPs);
    }
}
