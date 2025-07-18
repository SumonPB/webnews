package com.client.principal.logic.data.NetWork;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionEP {
    private String id;
    private subscriptionTypes name;
    private String descripcion;
    private Boolean contAnt;
    private Boolean cat2;
    private Boolean Fullcat;
}
