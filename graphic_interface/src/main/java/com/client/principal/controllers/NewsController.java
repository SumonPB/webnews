package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.Network.NewsUI;
import com.client.principal.logic.Network.UserUI;
import com.client.principal.logic.data.newtwork.NewsDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class NewsController {
    @Autowired
    NewsUI newsUI;
    @Autowired
    UserUI userUI;

    @PostMapping("/InsertNew")
    public String validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName,
            Model model) {

        NewsDAOEP noticia = newsUI.validarYCrearNoticia2(title, content, author, category, subscriptionName);
        model.addAttribute("noticia", noticia);
        return "redirect:/admin";
    }

    @PostMapping("/ModifyNews")
    public String modifyNews(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subscriptionId", required = false) String subscriptionId) {

        if (title != null && title.isBlank())
            title = null;
        if (content != null && content.isBlank())
            content = null;
        if (author != null && author.isBlank())
            author = null;
        if (category != null && category.isBlank())
            category = null;
        if (subscriptionId != null && subscriptionId.isBlank())
            subscriptionId = null;

        newsUI.modifyNews(id, title, content, author, category, subscriptionId);
        return "redirect:/admin";

    }

    @GetMapping("/GetAllNews")
    public List<NewsEP> getAllNews() {
        return newsUI.getAllNews();
    }

    @GetMapping("/seeNewsNoLog")
    public String seeNewsNoLog(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Invalidar sesión si existe
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Comprobar que no se ha regenerado
        HttpSession check = request.getSession(false);
        System.out.println("seenewsNOOOOlog:" + check); // Debería imprimir null

        // Desactivar caché
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        // Cargar noticias
        List<NewsEP> newsList = userUI.seeNewsNoLog();
        model.addAttribute("news", newsList);
        return "news_nolog";
    }

    @GetMapping("/seeNewsOnLog")
    public List<NewsEP> seeNewsOnLog(HttpSession session) {
        String email = (String) session.getAttribute("email");
        System.out.println("seenewsonlog:" + session);
        return userUI.seeNewsOnLog(email);
    }

}
