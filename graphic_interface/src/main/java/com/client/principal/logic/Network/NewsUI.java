package com.client.principal.logic.Network;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.newtwork.NewsDAOEP;
import com.client.principal.logic.data.newtwork.NewsEP;

@FeignClient(name = "NewsConsole", url = "http://localhost:9091")
public interface NewsUI {

    @GetMapping("/InsertNew")
    public NewsDAOEP validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName);

    @GetMapping("/ModifyNews")
    public NewsEP modifyNews(
            @RequestParam("id") String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subscriptionId", required = false) String subscriptionId);

    @GetMapping("/GetAllNews")
    public List<NewsUI> getAllNews();

}
