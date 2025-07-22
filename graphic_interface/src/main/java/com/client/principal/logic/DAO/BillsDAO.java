package com.client.principal.logic.DAO;

import java.time.LocalDateTime;

import com.client.principal.logic.data.newtwork.subscriptionTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillsDAO {

    private String id;
    private String paymentMethod;
    private Double amount;
    private LocalDateTime startSubscription;
    private LocalDateTime endSubscription;
    private String userId;
    private subscriptionTypes subscriptionName;

}
