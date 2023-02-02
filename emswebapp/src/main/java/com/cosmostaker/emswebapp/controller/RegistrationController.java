package com.cosmostaker.emswebapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cosmostaker.emswebapp.dto.RegistrationDTO;
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
        model.addAttribute("user", new RegistrationDTO());
        return "register";
    }


    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDTO registrationDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        }
        
        userService.save(registrationDTO);
        return "redirect:/register?success";
    }
}
