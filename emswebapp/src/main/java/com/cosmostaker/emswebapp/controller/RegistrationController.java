package com.cosmostaker.emswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cosmostaker.emswebapp.dao.RegistrationDAO;
import com.cosmostaker.emswebapp.service.IUserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
 
    public IUserService userService;


    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }
    

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDAO());
        return "register";
    }


    @PostMapping
    public String registerUser(@ModelAttribute("user") RegistrationDAO registrationDAO) {
        userService.save(registrationDAO);
        return "redirect:/register?success";
    }
}
