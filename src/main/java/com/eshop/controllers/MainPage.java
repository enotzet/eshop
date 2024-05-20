package com.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPage {

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("title", "Mainpage");
        return "mainpage";
    }
}
