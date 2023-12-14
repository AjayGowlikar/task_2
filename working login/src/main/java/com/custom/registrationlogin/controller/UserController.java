package com.custom.registrationlogin.controller;
import com.custom.registrationlogin.entity.User;
import com.custom.registrationlogin.service.UserDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
//@RequestMapping("/api/v1")
public class UserController
{
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @GetMapping("/register")
    public String saveUser(Model model)
    {
        model.addAttribute("user",new User("ROLE_USER"));
        return "registration";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user)
    {
        System.out.println(user);
        userDao.addUser(user);
        return "redirect:/login";
    }

//    @GetMapping

    @GetMapping("/login")
    public String login(){
        System.out.println("login");
        return "login";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("home")
    public String home(Principal principal, Model model)
    {
        if (principal!=null)
        {
            User user = userDao.getUserByUsername(principal.getName());
            model.addAttribute("user",user);
            System.out.println(user);
        }
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("fail")
    public String failed(){
        return "fail";
    }

}
