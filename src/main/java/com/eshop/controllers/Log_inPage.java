package com.eshop.controllers;

import com.eshop.model.goods;
import com.eshop.model.users;
import com.eshop.repo.goodsRepository;
import com.eshop.repo.usersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class Log_inPage {
    @Autowired
    private usersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/sign_up")
    public String redirect_to_sign_upPage(Model model) {
        return "sign_up";
    }
    @PostMapping("/log_in")
    public String loginLogic(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model, HttpSession session)
    {
        users user = usersRepository.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        else
        {
            model.addAttribute("message","incorrect email or password");
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            return "/log_in";
        }
    }
}
