package com.client.principal.logic.data.newtwork;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewsDAOEP {
    private CategoryNews categoryNews;
    private subscriptionTypes subscriptionType;
    private String title;
    private String author;
    private String content;

}
