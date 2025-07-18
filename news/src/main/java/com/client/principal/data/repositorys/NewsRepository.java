package com.client.principal.data.repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.client.principal.data.entities.News;
import com.client.principal.logic.data.CategoryNews;

public interface NewsRepository extends MongoRepository<News, String> {
    @Query("{ 'id' : ?0 }")
    public News getNewsById(String id);

    List<News> findBySubscriptionId(String subscriptionId);

    List<News> findBySubscriptionIdIn(List<String> subscriptionIds);

    List<News> findBySubscriptionIdInAndCategoryIn(List<String> subscriptionIds, List<CategoryNews> categories);

}
