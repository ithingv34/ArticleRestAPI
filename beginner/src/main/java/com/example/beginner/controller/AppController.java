package com.example.beginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// html 파일과 연결
@Controller
public class AppController {

    @GetMapping("/greeting")
    public String testConnection(Model model){
        model.addAttribute("username", "ithingv34");
        // templetes/greetings.mustache
        return "greetings";
    }
}
