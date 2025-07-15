package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.principal.logic.NewsUC;
import com.client.principal.logic.data.NewsUI;
import com.client.principal.logic.data.NetWork.AdminEP;

@RestController
public class NewsControler {
    @Autowired
    private NewsUC newsUC;

    @GetMapping("/InsertNew")
    public String insertNew(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionId") String subscriptionId) {
        newsUC.createNews(title, content, author, category, subscriptionId);
        return "Noticia creada con exito: " + title;
    }

    @GetMapping("/GetAllNews")
    public List<NewsUI> getAllNews() {
        return newsUC.getAllNews();
    }

    @GetMapping("/GetAdminByName")
    public AdminEP getAdminByName(@RequestParam("name") String name) {
        try {
            return newsUC.getAdminByName(name);
        } catch (Exception e) {
            return null;
        }
    }

}
