package com.client.principal.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.data.entities.Subscription;
import com.client.principal.logic.SubscriptionUC;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

@RestController
@CrossOrigin(origins = "https://tuinfoinsta.netlify.app/")
public class SubscriptionController {
    @Autowired
    private SubscriptionUC subscriptionUC;

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

    @GetMapping("/findbyid")
    public Subscription findSubscriptionById(@RequestParam("id") String idParam) {
        String cleanId = idParam.contains(",") ? idParam.split(",")[0] : idParam;

        Subscription subscription = subscriptionUC.geSubscriptionById(cleanId);
        if (subscription == null) {
            return subscription;
        }
        return subscription;
    }

    @GetMapping("/findByName")
    public List<Subscription> findByName(@RequestParam("userSub") List<subscriptionTypes> userSub) {
        return subscriptionUC.findByName(userSub);
    }

}
