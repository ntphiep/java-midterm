package com.hiep.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class HomeController {
    public static final String baseUrl = "http://localhost:8080";

    @GetMapping("/")
    public String getHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHome(Model model,HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/api/product/get-all";
        List products = restTemplate.getForObject(url, List.class);

        url = baseUrl + "/api/category/get-all";
        Iterable categories = restTemplate.getForObject(url, Iterable.class);

        url = baseUrl + "/api/brand/get-all";
        Iterable brands = restTemplate.getForObject(url, Iterable.class);
 
        if(model.getAttribute("products") == null) model.addAttribute("products", products);
        
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);

        return "index";
    }

    @PostMapping("/home")
    public String postHome() {
        return "index";
    }

    @GetMapping("/home/brand/{id}")
    public String getFilterByBrand(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        RestTemplate restTemplate = new RestTemplate();
        String url = baseUrl + "/api/product/find-by-brand-id/" + id;

        List products = restTemplate.getForObject(url, List.class);
        redirectAttributes.addFlashAttribute("products", products);
        return "redirect:/home";
    }


    @PostMapping("/home/filter-price")
    public String postPriceFilter(@RequestBody String body, RedirectAttributes redirectAttributes, HttpServletRequest request){
        Double price = Double.valueOf(body.split("=")[1]);
        RestTemplate restTemplate = new RestTemplate();

        String url = baseUrl + "/api/product/find-by-range-price/" + price;
        List products = restTemplate.getForObject(url, List.class);

        redirectAttributes.addFlashAttribute("products", products);
        redirectAttributes.addFlashAttribute("rangePrice", price);
        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String goLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
