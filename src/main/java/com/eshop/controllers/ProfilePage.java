package com.eshop.controllers;

import com.eshop.model.users;
import com.eshop.repo.usersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfilePage {
    @Autowired
    private usersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/profile")
    public String showLoginPage(HttpSession httpSession, Model model)
    {
        users user = (users)httpSession.getAttribute("user");
        if (httpSession.getAttribute("user")==null)
            return "redirect:/";
       model.addAttribute("email", user.getEmail());
       return "profile";
    }
    @PostMapping("/changeInformation")
    public String editingParams(@RequestParam("email") String email, @RequestParam("password")String password, Model model, HttpSession httpSession)
    {
        users user = (users)httpSession.getAttribute("user");
        if(!email.equals(user.getEmail()))
        {
            users check = usersRepository.findByEmail(email);
            if (check==null)
            {
                user.setEmail(email);
                usersRepository.save(user);
                httpSession.setAttribute("user", user);
                model.addAttribute("suc_message", "information was successfully updated");
            }
            else
            {
                model.addAttribute("message", "user with this email already exists!");
            }
        }
        if (!password.isEmpty())
        {
           if (password.length()<4)
               model.addAttribute("message", "password must contain at least 4 symbols");
           else
           {
               user.setPassword(bCryptPasswordEncoder.encode(password));
               usersRepository.save(user);
               httpSession.setAttribute("user", user);
               model.addAttribute("suc_message", "information was successfully updated");
           }
        }
        model.addAttribute("email", email);
        return "profile";
    }
}
