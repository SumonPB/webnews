package com.client.principal.data.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.client.principal.logic.data.CategoryNews;

@Data
@Builder
@Document(collection = "News")
public class News {

    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private CategoryNews category;
    private String subscriptionId;

}
