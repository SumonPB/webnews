package com.client.principal.data.repositorys;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.News;

public interface NewsRepository extends MongoRepository<News, String> {
    @Query("{ 'id' : ?0 }")
    public News getNewsById(String id);
}
