package com.client.principal.logic.DTO;

import com.client.principal.data.entities.Client;
import com.client.principal.logic.DAO.ClientDao;
import com.client.principal.logic.data.ClientUI;

public class ClientDTO {

    public static ClientUI toClientUI(Client client) {
        return ClientUI.builder()
                .id(client.getId())
                .name(client.getName())
                .nickname(client.getNickname())
                .email(client.getEmail())
                .password(client.getPassword())
                .role(client.getRole())
                .category(client.getCategory())
                .subscriptionID(client.getSubscriptionID())
                .build();
    }

    public static Client toClient(ClientUI clientUI) {
        return Client.builder()
                .name(clientUI.getName())
                .nickname(clientUI.getNickname())
                .email(clientUI.getEmail())
                .password(clientUI.getPassword())
                .role(clientUI.getRole())
                .category(clientUI.getCategory())
                .subscriptionID(clientUI.getSubscriptionID())
                .build();
    }

    public static ClientDao toClientDao(Client client) {
        return ClientDao.builder()
                .name(client.getName())
                .nickname(client.getNickname())
                .email(client.getEmail())
                .category(client.getCategory())
                .subscriptionID(client.getSubscriptionID())
                .build();
    }

}
