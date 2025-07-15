package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.NewsUC;
import com.client.principal.logic.data.NewsUI;

@RestController
public class NewsControler {
    @Autowired
    private NewsUC newsUC;

    // primera forma
    @GetMapping("/valUser/InsertNew")
    public String validarYCrearNoticia(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionId") String subscriptionId) {

        if (newsUC.validateAdmin(email, password)) {
            newsUC.createNews(title, content, author, category, subscriptionId);
            return "Noticia creada por el administrador: " + title;
        } else {
            return "Usuario no autorizado para crear noticias";
        }
    }

    // segunda forma
    @GetMapping("/InsertNew")
    public String validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionId") String subscriptionId) {
        newsUC.createNews(title, content, author, category, subscriptionId);
        return "Noticia creada por el administrador: " + title;

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

}
