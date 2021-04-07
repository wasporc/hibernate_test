package ru.hiber.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPath {

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }
}
