package com.client.principal.logic.data.network;

import com.client.principal.logic.data.CategoryNews;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsEP {
    private String id;
    private String title;
    private String content;
    private String author;
    private CategoryNews category;
    private String subscriptionId;
    private String imgUrl;
}
