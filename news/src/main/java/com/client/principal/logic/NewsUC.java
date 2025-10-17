package com.client.principal.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.principal.data.entities.News;
import com.client.principal.data.repositorys.NewsRepository;
import com.client.principal.logic.DAO.NewsDAO;
import com.client.principal.logic.DTO.NewsDTO;
import com.client.principal.logic.NetWork.AdminValidation;
import com.client.principal.logic.NetWork.GetSubscription;
import com.client.principal.logic.Validations_Encriptations.AllowedTypes;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NewsUI;
import com.client.principal.logic.data.NetWork.SubscriptionEP;
import com.client.principal.logic.data.NetWork.subscriptionTypes;

@Service
public class NewsUC {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private AdminValidation adminValidation;

    @Autowired
    private GetSubscription getSubscription;

    public NewsDAO createNews(
            String title,
            String content,
            String author,
            String category,
            String subscriptionName,
            String imageUrl) {

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
                .subscriptionId(getSubscription.GetSubscriptionByName(subscriptionName).getId())
                .imgUrl(imageUrl)
                .build();
        newsRepository.save(news);
        return NewsDTO.newsFormat(news, getSubscription.GetSubscriptionByName(subscriptionName).getName());
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
            String subscriptionName,
            String imageUrl) {

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

        if (subscriptionName != null) {

            news.setSubscriptionId(getSubscription.GetSubscriptionByName(subscriptionName).getId());
        }

        if (imageUrl != null) {
            news.setImgUrl(imageUrl);
        }

        newsRepository.save(news);
        return NewsDTO.toNewsUI(news);
    }

    public Boolean validateAdmin(String email, String password) {

        return adminValidation.getAdminByName(email, password);
    }

    public List<NewsUI> obtenerNoticiasPermitidas(String nameSub, List<CategoryNews> categoriasFiltradas) {

        SubscriptionEP subscription = getSubscription.GetSubscriptionByName(nameSub);

        List<subscriptionTypes> tiposPermitidos = AllowedTypes.tiposPermitidos(subscription);

        List<SubscriptionEP> subsPermitidas = getSubscription.findByName(tiposPermitidos);

        List<String> idsSubsPermitidas = subsPermitidas.stream()
                .map(SubscriptionEP::getId)
                .collect(Collectors.toList());

        List<News> newsList;

        if (categoriasFiltradas == null || categoriasFiltradas.isEmpty()) {

            newsList = newsRepository.findBySubscriptionIdIn(idsSubsPermitidas);
        } else {

            newsList = newsRepository.findBySubscriptionIdInAndCategoryIn(idsSubsPermitidas, categoriasFiltradas);
        }
        return newsList.stream()
                .map(NewsDTO::toNewsUI)
                .collect(Collectors.toList());
    }

}
