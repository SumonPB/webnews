package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.client.principal.data.entities.News;

public interface NewsRepository extends MongoRepository<News, String> {

}
