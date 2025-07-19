package com.client.principal.logic.NETWORK;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.network.NewsEP;

@FeignClient(name = "ViewNews", url = "http://localhost:9091")
public interface ViewNews {
    @GetMapping("/GetNewsByClient")
    public List<NewsEP> GetNewsByClient(
            @RequestParam("nameSub") String nameSub,
            @RequestParam(value = "categoriasFiltradas") List<CategoryNews> categoriasFiltradas);
}
