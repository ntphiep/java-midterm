package com.hiep.controllers;

import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.hiep.models.User;


@Controller
public class UserController {
    public static final String baseUrl = "http://localhost:8080";

    @GetMapping("/user")   // [GET] login/register for user
    public String getUser(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    model.addAttribute("remUsername", cookie.getValue());
                }
                if (cookie.getName().equals("password")) {
                    model.addAttribute("remPassword", cookie.getValue());
                }
            }
        }

        if (session.getAttribute("userLogin") != null) {
            redirectAttributes.addFlashAttribute("message", "Login success!");
            return "redirect:/";
        }

        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/user/login")      // [GET] /user/login
    public String getLogin() {
        return "redirect:/user";
    }

    @PostMapping("/user/login")     // [POST] /user/login
    public String postLogin(User user, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        String api = "/api/user/login/user-valid";
        String url = baseUrl + api;
        String requestJson = "{\"username\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\"}";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String strUser = restTemplate.postForObject(url, entity, String.class);
        Gson gson = new Gson();
        User userValid = gson.fromJson(strUser, User.class);
        if (userValid != null) {
            HttpSession session = request.getSession();
            Cookie ck = new Cookie("username", user.getUsername());
            Cookie ck1 = new Cookie("password", user.getPassword());

            response.addCookie(ck);
            response.addCookie(ck1);
            session.setAttribute("userLogin", userValid);
        } else redirectAttributes.addFlashAttribute("message", "Password or Username invalid!");

        return "redirect:/user";
    }


    @GetMapping("/user/signup")         // [GET] /user/signup
    public String getSignUp() {
        return "redirect:/user";
    }


    @PostMapping("/user/signup")
    public String postSignUp(User user, RedirectAttributes redirectAttributes) {
        String api = "/api/user/add";
        String url = baseUrl + api;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        Gson gson = new Gson();
        String requestJson = gson.toJson(user);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String strMessage = restTemplate.postForObject(url, entity, String.class);
        redirectAttributes.addFlashAttribute("messageRegister", strMessage);
        return "redirect:/user";
    }
}
