package com.client.principal.logic.DAO;

import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.network.subscriptionTypes;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsDao {
    private String id;
    private String title;
    private String content;
    private String author;
    private CategoryNews category;
    private subscriptionTypes subscriptionName;
    private String imgUrl;
}
