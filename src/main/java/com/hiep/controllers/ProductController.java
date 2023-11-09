package com.hiep.controllers;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hiep.models.Product;


@Controller
public class ProductController {
    public static final String rootUrl = "http://localhost:8080";

    @GetMapping("/product/detail/{id}")
    public String getDetail(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String url = rootUrl + "/api/product/find/" + id;
        RestTemplate restTemplate = new RestTemplate();
        String strProduct = restTemplate.getForObject(url, String.class);

        try {
            Gson g = new Gson();
            Product product = g.fromJson(strProduct, Product.class);
            model.addAttribute("product", product);
            return "product-details";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }


    @PostMapping("/product/search")
    public String goSearch(Model model, @RequestBody String request) {
        String text = request.split("txtSearch=")[1];
        String url = rootUrl + "/api/product/search/" + text;
        RestTemplate restTemplate = new RestTemplate();
        
        Iterable products = restTemplate.getForObject(url, Iterable.class);
        model.addAttribute("products", products);

        return "search";
    }
}
