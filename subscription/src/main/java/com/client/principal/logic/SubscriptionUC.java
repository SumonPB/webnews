package com.client.principal.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.Subscription;
import com.client.principal.data.repositorys.SubscriptionRepository;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

@Service
public class SubscriptionUC {
        @Autowired
        private SubscriptionRepository subscriptionRepository;

        public void Subscriptions() {

                subscriptionRepository.save(Subscription.builder()
                                .name(subscriptionTypes.INSIDER)
                                .Fullcat(true)
                                .cat2(false)
                                .contAnt(true)
                                .descripcion("Acceso a contenido de todas las categorías y contenido anticipado")
                                .build());

                subscriptionRepository.save(Subscription.builder()
                                .name(subscriptionTypes.REDACCIONPlus)
                                .Fullcat(true)
                                .cat2(false)
                                .contAnt(false)
                                .descripcion("Acceso a contenido de dos categorías")
                                .build());

                subscriptionRepository.save(Subscription.builder()
                                .name(subscriptionTypes.TITULAR)
                                .Fullcat(false)
                                .cat2(true)
                                .contAnt(false)
                                .descripcion("Acceso a contenido de todas las categorías")
                                .build());

                subscriptionRepository.save(Subscription.builder()
                                .name(subscriptionTypes.FREE)
                                .Fullcat(false)
                                .cat2(false)
                                .contAnt(false)
                                .descripcion("Contenido gratuito sin necesidad de pago")
                                .build());
        }

        public List<Subscription> allSubscription() {
                return subscriptionRepository.findAll();
        }

        public Subscription getSubscriptionByName(String name) {
                return subscriptionRepository.getSubscriptionByName(name);
        }

        public Subscription geSubscriptionById(String id) {
                return subscriptionRepository.findById(id).orElse(null);
        }

        public List<Subscription> findByName(List<subscriptionTypes> names) {
                return subscriptionRepository.findByNameIn(names);
        }
}
