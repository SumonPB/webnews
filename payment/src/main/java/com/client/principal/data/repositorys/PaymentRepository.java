package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    @Query("{ 'id' : ?0 }")
    public Payment getBillById(String id);

}
