package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    @Query("{ 'name' : ?0 }")
    public Subscription getSubscriptionByName(String name);

    @Query("{ '_id' : ?0 }")
    public Subscription getSubscriptionById(String id);
}
