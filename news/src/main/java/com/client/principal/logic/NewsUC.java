package com.client.principal.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Ne;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.News;
import com.client.principal.data.repositorys.NewsRepository;
import com.client.principal.logic.DTO.NewsDTO;
import com.client.principal.logic.NetWork.AdminValidation;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NewsUI;

@Service
public class NewsUC {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AdminValidation adminValidation;

    public NewsUI createNews(
            String title,
            String content,
            String author,
            String category,
            String subscriptionId) {

        CategoryNews categoryEnum;
        try {
            categoryEnum = CategoryNews.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Categoría no válida: " + category);
        }

        News news = News.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(categoryEnum)
                .subscriptionId(subscriptionId)
                .build();
        newsRepository.save(news);
        return NewsDTO.toNewsUI(news);
    }

    public List<NewsUI> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(NewsDTO::toNewsUI)
                .toList();
    }

    public NewsUI updateNews(
            String id,
            String title,
            String content,
            String author,
            String category,
            String subscriptionId) {

        ;
        News news = newsRepository.getNewsById(id);

        if (news == null) {
            throw new RuntimeException("Noticia no encontrada con ID: " + id);
        }

        if (title != null) {
            news.setTitle(title);
        }

        if (content != null) {
            news.setContent(content);
        }

        if (author != null) {
            news.setAuthor(author);
        }

        if (category != null) {
            try {
                CategoryNews categoryEnum = CategoryNews.valueOf(category.toUpperCase());
                news.setCategory(categoryEnum);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Categoría no válida: " + category);
            }
        }

        if (subscriptionId != null) {
            news.setSubscriptionId(subscriptionId);
        }

        newsRepository.save(news);
        return NewsDTO.toNewsUI(news);
    }

    public Boolean validateAdmin(String email, String password) {

        return adminValidation.getAdminByName(email, password);
    }
}
