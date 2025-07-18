package com.client.principal.data.repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.Subscription;
import com.client.principal.logic.data.newtwork.subscriptionTypes;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    @Query("{ 'name' : ?0 }")
    public Subscription getSubscriptionByName(String name);

    @Query("{ '_id' : ?0 }")
    public Subscription getSubscriptionById(String id);

    List<Subscription> findByNameIn(List<subscriptionTypes> names);

}
