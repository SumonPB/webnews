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
    public String InserSubscriptionType() {
        subscriptionUC.Subscriptions();
        return "Ingresado correctamente";
    }

    @GetMapping("/GetAllSubscriptions")
    public List<Subscription> GetAllSubscriptions() {

        return subscriptionUC.allSubscription();
    }

    @GetMapping("/GetSubscriptionByName")
    public Subscription GetSubscriptionByName(@RequestParam String name) {
        return subscriptionUC.getSubscriptionByName(name);
    }

}
