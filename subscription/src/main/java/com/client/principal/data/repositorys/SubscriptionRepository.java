package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.client.principal.data.entities.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String>{
    
}
