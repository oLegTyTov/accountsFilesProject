package com.example.accountsfiles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.accountsfiles.entities.Account;
import com.example.accountsfiles.services.ServiceAccount;

import jakarta.transaction.Transactional;

@Controller
public class AccountController {

    private final ServiceAccount serviceAccount;

    public AccountController(ServiceAccount serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    @GetMapping("/signup")
    public String signupForm() {
        
        return "html/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Account account) {

        if(serviceAccount.addAccount(account)){
            return "redirect:/";
        }
        else{
        return "redirect:/signup?error=true";
        }
    }

    @GetMapping("/deleteAccount")
    public String deleteAccountForm() {
        return "html/deleteAccount";
    }
    @Transactional
    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam String name,@RequestParam String password) {
        if(serviceAccount.deleteAccount(name, password))
        {
            return "redirect:/";
        }
        else{
            return "redirect:/deleteAccount?error=true";
        }
    }
}
