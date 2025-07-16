package com.client.principal.data.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Payment")
public class Payment {
    @Id
    private String id;
    private String paymentMethod;
    private Double amount;
    private Long startSubscription;
    private Long endSubscription;
    private String userId;
    private String subscriptionId;

}
