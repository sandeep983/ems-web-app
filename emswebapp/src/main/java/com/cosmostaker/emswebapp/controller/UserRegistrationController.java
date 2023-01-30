package com.cosmostaker.emswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cosmostaker.emswebapp.dao.UserRegistrationDAO;
import com.cosmostaker.emswebapp.service.IUserService;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
 
    public IUserService userService;


    public UserRegistrationController(IUserService userService) {
        this.userService = userService;
    }
    

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDAO());
        return "register";
    }


    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegistrationDAO registrationDAO) {
        userService.save(registrationDAO);
        return "redirect:/register?success";
    }
}
