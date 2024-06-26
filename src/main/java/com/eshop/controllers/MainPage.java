package com.eshop.controllers;

import com.eshop.repo.goodsRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.eshop.model.goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPage {
    @Autowired
    private goodsRepository goodsRepository;
    @GetMapping("/")
    public String mainPage(Model model, HttpSession session) {
        Iterable<goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        return "mainpage";
    }
    @GetMapping("/redirect/log_in")
    public String log_inPage(Model model) {
        return "log_in";
    }
    @GetMapping("/redirect/sign_up")
    public String sign_upPage(Model model) {
        return "sign_up";
    }
    @GetMapping("/logout")
    public String log_out(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
