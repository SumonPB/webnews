package com.client.principal.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Client;
import com.client.principal.data.repositorys.ClientRepository;
import com.client.principal.logic.DAO.ClientDao;
import com.client.principal.logic.DTO.ClientDTO;
import com.client.principal.logic.Validations_Encriptations.Cesar;
import com.client.principal.logic.data.ClientUI;

@Service
public class ClientUC {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Cesar cesar;

    public ClientUI createAdmin(String name,
            String nickname,
            String email,
            String password) {
        ClientUI client = ClientUI.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(true) // true for admin role
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
        }
        return cesar.decrypt(clientBD.getPassword()).equals(password);
    }

}
