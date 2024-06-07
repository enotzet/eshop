package com.eshop.controllers;

import com.eshop.model.goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Log_inPage {
    @GetMapping("/jj")
    public String log_inPage(Model model) {
        return "log_in";
    }
}
