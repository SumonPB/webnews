package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    @Query("{ 'name' : ?0 }")
    public Client getClientByName(String name);

    @Query("{ 'email': ?0 }")
    Client getClientByEmail(String email);

}
