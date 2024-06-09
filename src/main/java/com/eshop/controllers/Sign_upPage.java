package com.eshop.controllers;

import com.eshop.model.users;
import com.eshop.repo.usersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class Sign_upPage {
    @Autowired
    private usersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/login")
    public String redirect_to_loginPage(Model model)
    {
        return "/log_in";
    }
    @PostMapping("/sign_up")
    public String sign_upLogic(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("rep_password") String rep_password,
                             Model model, HttpSession session) {
        users user = usersRepository.findByEmail(email);
        if (user != null)
        {
            model.addAttribute("message","user with this email already exists");
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rep_password", rep_password);
            return "/sign_up";
        }
        else if (password.length()<4) {
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rep_password", rep_password);
            model.addAttribute("message","password must have at least 4 symbols");
            return "/sign_up";
        }
        else if (!password.equals(rep_password)) {
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            model.addAttribute("rep_password", rep_password);
            model.addAttribute("message","inputed passwords must be the same");
            return "/sign_up";
        } else
        {
            users user1 = new users();
            user1.setEmail(email);
            user1.setPassword(bCryptPasswordEncoder.encode(password));
            usersRepository.save(user1);
            session.setAttribute("user", user1);
            return "redirect:/";
        }
    }
}
