package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.data.entities.Subscription;
import com.client.principal.logic.SubscriptionUC;

@RestController
public class SubscriptionController {
    @Autowired
    SubscriptionUC subscriptionUC;
    
    @GetMapping("/InserSubscriptionType")
    public Subscription InserSubscriptionType(
            @RequestParam("type") String type) {


        return subscriptionUC.newSubscription(type);
    }

    @GetMapping("/GetAllSubscriptions")
    public List<Subscription> GetAllSubscriptions() {


        return subscriptionUC.allSubscription();
    }


}
