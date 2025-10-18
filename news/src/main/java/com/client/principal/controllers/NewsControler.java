package com.client.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.client.principal.logic.CloudService;
import com.client.principal.logic.NewsUC;
import com.client.principal.logic.DAO.NewsDAO;
import com.client.principal.logic.data.CategoryNews;
import com.client.principal.logic.data.NewsUI;

@RestController
@CrossOrigin(origins = "https://tuinfoinsta.netlify.app/")
public class NewsControler {
    @Autowired
    private NewsUC newsUC;
    @Autowired
    private CloudService cloudService;

    // primera forma
    /*
     * @PostMapping("/valUser/InsertNew")
     * public NewsDAO validarYCrearNoticia(
     * 
     * @RequestParam("email") String email,
     * 
     * @RequestParam("password") String password,
     * 
     * @RequestParam("title") String title,
     * 
     * @RequestParam("content") String content,
     * 
     * @RequestParam("author") String author,
     * 
     * @RequestParam("category") String category,
     * 
     * @RequestParam("subscriptionName") String subscriptionName,
     * 
     * @RequestParam("image") MultipartFile image) throws Exception {
     * 
     * String imageUrl = cloudService.uploadFile(image);
     * 
     * if (newsUC.validateAdmin(email, password)) {
     * return newsUC.createNews(title, content, author, category, subscriptionName,
     * imageUrl);
     * } else {
     * return null;
     * }
     * }
     */

    // segunda forma
    @PostMapping(value = "/InsertNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public NewsDAO validarYCrearNoticia2(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("category") String category,
            @RequestParam("subscriptionName") String subscriptionName,
            @RequestParam("image") MultipartFile image) throws Exception {

        String imageUrl = cloudService.uploadFile(image);
        return newsUC.createNews(title, content, author, category, subscriptionName, imageUrl);

    }

    //
    @GetMapping("/GetAllNews")
    public List<NewsUI> getAllNews() {
        return newsUC.getAllNews();
    }

    //
    @PostMapping("/ModifyNews")
    public NewsUI modifyNews(
            @RequestParam("id") String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "subscriptionName", required = false) String subscriptionName,
            @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {

        if (id.contains(",")) {
            id = id.split(",")[0];
        }
        if (id == null || id.isEmpty()) {
            throw new RuntimeException("ID de noticia no puede ser nulo o vacío");
        }
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = cloudService.uploadFile(image);
        }
        return newsUC.updateNews(id, title, content, author, category, subscriptionName, imageUrl);

    }

    @GetMapping("/GetNewsByClient")
    public List<NewsUI> GetNewsByClient(
            @RequestParam("nameSub") String nameSub,
            @RequestParam(value = "categoriasFiltradas", required = false) List<CategoryNews> categoriasFiltradas) {

        return newsUC.obtenerNoticiasPermitidas(nameSub, categoriasFiltradas);
    }

}
