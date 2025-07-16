package com.client.principal.data.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "Subscriptions")
public class Subscription {

    @Id
    private String id;
    @Indexed(unique = true)
    private subscriptionTypes name;
    private String descripcion;
    private Boolean contAnt;
    private Boolean cat2;
    private boolean Fullcat;

}
