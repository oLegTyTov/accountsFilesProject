package com.example.accountsfiles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String mainPage() {
        return "html/mainPage";
    }

    @GetMapping("/login")
    public String login() {
        return "html/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
