package com.client.principal.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Subscription;
import com.client.principal.data.repositorys.SubscriptionRepository;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

@Service
public class SubscriptionUC {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    public Subscription newSubscription(String type) {
        switch (type) {
            case "INSIDER":
            
                        return subscriptionRepository.save(Subscription.builder()
                        .name(subscriptionTypes.INSIDER)
                        .Fullcat(true)
                        .cat2(false)
                        .contAnt(true)
                        .descripcion("Acceso a contenido de todas las categorias y contenido anticipado")
                        .build()); 
                
            case "REDACCION":
                return subscriptionRepository.save(Subscription.builder()
                        .name(subscriptionTypes.REDACCION)
                        .Fullcat(false)
                        .cat2(true)
                        .contAnt(false)
                        .descripcion("Acceso a contenido de dos categorias")
                        .build());
            case "TITULARPlus":
                return subscriptionRepository.save(Subscription.builder()
                        .name(subscriptionTypes.TITULARPlus)
                        .Fullcat(true)
                        .cat2(false)
                        .contAnt(false)
                        .descripcion("Acceso a contenido de todas las categorias")
                        .build());
            default:
                        return subscriptionRepository.save(Subscription.builder()
                        .name(subscriptionTypes.FREE)
                        .Fullcat(false)
                        .cat2(false)
                        .contAnt(false)
                        .descripcion("Contenido gratuito sin necesidad de pago")
                        .build());
        }
    }
    public List<Subscription> allSubscription(){
        return subscriptionRepository.findAll();
    }
}
