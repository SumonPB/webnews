package com.client.principal.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.client.principal.logic.data.CategoryNews;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "Client")
public class Client {

    @Id
    private String id;
    private String name;
    private String nickname;
    @Indexed(unique = true)
    private String email;
    private String password;
    private Boolean role;
    private String subscriptionID;
    private List<CategoryNews> category;
}
