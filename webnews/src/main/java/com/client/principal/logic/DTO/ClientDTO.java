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
                .build();
    }

    public static Client toClient(ClientUI clientUI) {
        return Client.builder()
                .name(clientUI.getName())
                .nickname(clientUI.getNickname())
                .email(clientUI.getEmail())
                .password(clientUI.getPassword())
                .role(clientUI.getRole())
                .build();
    }

    public static ClientDao toClientDao(Client Client) {
        return ClientDao.builder()
                .name(Client.getName())
                .nickname(Client.getNickname())
                .email(Client.getEmail())
                .build();
    }

}
