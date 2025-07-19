package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.Network.NewsUI;
import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.NewsDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;

import jakarta.servlet.http.HttpSession;

@Controller
public class NewsController {
    @Autowired
    NewsUI newsUI;
    @Autowired
    UserUI userUI;

    @GetMapping("/InsertNew")
    public NewsDAOEP validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName) {
        return newsUI.validarYCrearNoticia2(title, content, author, category, subscriptionName);
    }

    @GetMapping("/ModifyNews")
    public NewsEP modifyNews(
            @RequestParam("id") String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subscriptionId", required = false) String subscriptionId) {
        return newsUI.modifyNews(id, title, content, author, category, subscriptionId);
    }

    @GetMapping("/GetAllNews")
    public List<NewsEP> getAllNews() {
        return newsUI.getAllNews();
    }

    @GetMapping("/seeNewsNoLog")
    public String seeNewsNoLog(Model model) {
        List<NewsEP> newsList = userUI.seeNewsNoLog();
        model.addAttribute("news", newsList);
        return "news_nolog";
    }

    @GetMapping("/seeNewsOnLog")
    public List<NewsEP> seeNewsOnLog(HttpSession session) {
        String email = (String) session.getAttribute("email");
        return userUI.seeNewsOnLog(email);
    }

}
