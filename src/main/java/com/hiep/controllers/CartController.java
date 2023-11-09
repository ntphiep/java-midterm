package com.hiep.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.hiep.models.User;

import java.util.ArrayList;
import java.util.Map;


@Controller
public class CartController {
    public static final String rootUrl = "http://localhost:8080";

    @GetMapping("/cart")        // [GET] /cart
    public String getCart(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null)  return "redirect:/user";
        
        User user = (User) session.getAttribute("userLogin");
        model.addAttribute("user", user);

        String url = rootUrl + "/api/cart/all-by-id/" + user.getId();

        RestTemplate restTemplate = new RestTemplate();
        Iterable carts = restTemplate.getForObject(url, Iterable.class);

        if (carts == null) carts = new ArrayList<>();
        
        model.addAttribute("carts", carts);
        
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCartID(@PathVariable("id") Long product_id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) return "redirect:/user";
        
        User user = (User) session.getAttribute("userLogin");
        String url = rootUrl + "/api/cart/add";
        
        int quantity = 0;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String requestJson = "{\"user_id\":\"" + user.getId() + "\",\"product_id\":\"" + product_id + "\",\"quantity\":\"" + quantity + "\"}";

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        restTemplate.postForObject(url, entity, String.class);

        return "redirect:/cart";
    }


    @PostMapping("/cart/add")       // [POST] /cart/add
    public String addToCart(@RequestBody Map<String, String> body, HttpServletRequest request){
        HttpSession session = request.getSession();

        if (session.getAttribute("userLogin") == null) return "redirect:/user";

        User user = (User) session.getAttribute("userLogin");
        int quantity = Integer.parseInt(body.get("quantity"));
        long product_id = Long.parseLong(body.get("product_id"));

        String url = rootUrl + "/api/cart/add";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        String requestJson = "{\"user_id\":\"" + user.getId() + "\",\"product_id\":\"" + product_id + "\",\"quantity\":\"" + quantity + "\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        restTemplate.postForObject(url, entity, String.class);
        
        return "redirect:/cart";
    }



    @GetMapping("/cart/delete/{id}")        
    public String deleteFromCart(@PathVariable("id") Long id) {
        String url = rootUrl + "/api/cart/delete/" + id;
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(url);
        return "redirect:/cart";
    }
}


