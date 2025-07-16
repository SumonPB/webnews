package com.client.principal.logic.DAO;

import java.time.LocalDateTime;

import com.client.principal.logic.data.newtwork.subscriptionTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDAO {
    private String billID;
    private String clientName;
    private subscriptionTypes subscriptionName;
    private LocalDateTime startSubscription;
    private LocalDateTime endSubscription;
    private String payMethod;
    private Double amount;

}
