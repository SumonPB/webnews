package com.client.principal.logic.DTO;

import com.client.principal.data.entities.News;
import com.client.principal.logic.DAO.NewsDAO;
import com.client.principal.logic.data.NewsUI;
import com.client.principal.logic.data.NetWork.subscriptionTypes;

public class NewsDTO {
    public static NewsUI toNewsUI(News news) {
        if (news == null) {
            return null;
        }
        return NewsUI.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .author(news.getAuthor())
                .category(news.getCategory())
                .subscriptionId(news.getSubscriptionId())
                .build();
    }

    public static News toNews(NewsUI newsUI) {
        if (newsUI == null) {
            return null;
        }
        return News.builder()
                .id(newsUI.getId())
                .title(newsUI.getTitle())
                .content(newsUI.getContent())
                .author(newsUI.getAuthor())
                .category(newsUI.getCategory())
                .subscriptionId(newsUI.getSubscriptionId())
                .build();
    }

    public static NewsDAO newsFormat(News news, subscriptionTypes subscriptionType) {
        return NewsDAO.builder()
                .categoryNews(news.getCategory())
                .subscriptionType(subscriptionType)
                .title(news.getTitle())
                .author(news.getAuthor())
                .content(news.getContent())
                .build();
    }

}
