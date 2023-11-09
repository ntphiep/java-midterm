package com.hiep.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hiep.models.Customer;
import com.hiep.models.User;

import java.util.ArrayList;




@Controller
public class CheckoutController {
    public static final String rootUrl = "http://localhost:8080";

    @GetMapping("/checkout") 
    public String getCheckOut(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) return "redirect:/user";
        
        User user = (User) session.getAttribute("userLogin");
        String url = rootUrl + "/api/cart/all-by-id/" + user.getId();

        RestTemplate restTemplate = new RestTemplate();
        Iterable carts = restTemplate.getForObject(url, Iterable.class);
        if (carts == null) carts = new ArrayList<>();

        model.addAttribute("carts", carts);

        url = rootUrl + "/api/cart/total/" + user.getId();
        restTemplate = new RestTemplate();
        Double total = restTemplate.getForObject(url, Double.TYPE);
        
        model.addAttribute("total", total);
        model.addAttribute("user", user);

        url = rootUrl + "/api/customer/find/" + user.getCustomer().getId();
        restTemplate = new RestTemplate();

        String strCustomer = restTemplate.getForObject(url,String.class);
        Gson gson = new Gson();
        Customer customer = gson.fromJson(strCustomer, Customer.class);
        model.addAttribute("customer", customer);

        return "checkout";
    }   


    @GetMapping("/checkout/order")
    public String getOrder() {
        return "redirect:/checkout";
    }


    @PostMapping("/checkout/order")
    public String postOrder(Customer customer, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") == null) return "redirect:/user";
        
        User user = (User) session.getAttribute("userLogin");
        String url = rootUrl + "/api/cart/all-by-id/" + user.getId();

        RestTemplate restTemplate = new RestTemplate();
        String carts = restTemplate.getForObject(url, String.class);

        if (carts == null) {
            redirectAttributes.addFlashAttribute("message", "Empty cart!");
            return "redirect:/checkout";
        }
        
        if(customer.containNone()) {
            redirectAttributes.addFlashAttribute("message","Please enter your full information!");
            return "redirect:/checkout";
        }

        // Update information customer
        url = rootUrl + "/api/customer/update";
        Gson gson = new Gson();
        String requestJson = gson.toJson(customer);
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        restTemplate.postForObject(url, entity, String.class);

        // Add order
        url = rootUrl + "/api/order/add";
        requestJson = "{\"user_id\":\"" + user.getId() + "\",\"customer_id\":\"" + customer.getId() + "\"}";
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.postForObject(url, new HttpEntity<>(requestJson, headers), String.class);
        redirectAttributes.addFlashAttribute("message","Order Successfully!");

        return "redirect:/checkout";
    }
}
