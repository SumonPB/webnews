package com.client.principal.logic.data;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientUI {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private Boolean role;
    private String subscriptionID;
    private List<CategoryNews> category;
    private List<String> billsId;

}
