package com.client.principal.logic.data.newtwork;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDAOEP {
    private String name;
    private String nickname;
    private String email;
    private String subscriptionID;
    private List<CategoryNews> category;
    private List<String> billsId;
}
