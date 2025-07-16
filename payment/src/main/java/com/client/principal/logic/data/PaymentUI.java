package com.client.principal.logic.data;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentUI {
    private String id;
    private String paymentMethod;
    private Double amount;
    private LocalDateTime startSubscription;
    private LocalDateTime endSubscription;
    private String userId;
    private String subscriptionId;

}
