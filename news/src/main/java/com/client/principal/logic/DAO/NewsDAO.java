package com.client.principal.logic.DAO;

import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NetWork.subscriptionTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsDAO {
    private CategoryNews categoryNews;
    private subscriptionTypes subscriptionType;
    private String title;
    private String author;
    private String content;
}
