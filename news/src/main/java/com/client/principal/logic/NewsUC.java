package com.client.principal.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.News;
import com.client.principal.data.repositorys.NewsRepository;
import com.client.principal.logic.DTO.NewsDTO;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NewsUI;

@Service
public class NewsUC {
    @Autowired
    private NewsRepository newsRepository;

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

}
