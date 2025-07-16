package com.client.principal.logic.DAO;

import java.util.ArrayList;
import java.util.List;

import com.client.principal.logic.data.CategoryNews;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDao {
    private String name;
    private String nickname;
    private String email;
    private String subscriptionID;
    private List<CategoryNews> category;
}
