package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.NewsUC;
import com.client.principal.logic.DAO.NewsDAO;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NewsUI;
import com.client.principal.logic.data.NetWork.SubscriptionEP;
import com.client.principal.logic.data.NetWork.subscriptionTypes;

@RestController
public class NewsControler {
    @Autowired
    private NewsUC newsUC;

    // primera forma
    @GetMapping("/valUser/InsertNew")
    public NewsDAO validarYCrearNoticia(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName) {

        if (newsUC.validateAdmin(email, password)) {
            return newsUC.createNews(title, content, author, category, subscriptionName);
        } else {
            return null;
        }
    }

    // segunda forma
    @GetMapping("/InsertNew")
    public NewsDAO validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName) {

        return newsUC.createNews(title, content, author, category, subscriptionName);

    }

    //
    @GetMapping("/GetAllNews")
    public List<NewsUI> getAllNews() {
        return newsUC.getAllNews();
    }

    //
    @GetMapping("/ModifyNews")
    public NewsUI modifyNews(
            @RequestParam("id") String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subscriptionId", required = false) String subscriptionId) {

        if (id.contains(",")) {
            id = id.split(",")[0];
        }
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("ID de noticia no puede ser nulo o vacío");
        } else {
            return newsUC.updateNews(id, title, content, author, category, subscriptionId);
        }

    }

    @GetMapping("/GetNewsByClient")
    public List<NewsUI> GetNewsByClient(
            @RequestParam("nameSub") String nameSub,
            @RequestParam(value = "categoriasFiltradas", required = false) List<CategoryNews> categoriasFiltradas) {

        return newsUC.obtenerNoticiasPermitidas(nameSub, categoriasFiltradas);
    }

}
