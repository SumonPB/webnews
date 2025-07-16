package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.client.principal.data.entities.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
