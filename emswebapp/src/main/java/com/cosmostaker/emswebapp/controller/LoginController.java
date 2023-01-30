package com.cosmostaker.emswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showMyLoginPage() {
		
		return "login";
	}
	

	// add request mapping for /accessDenied
	@GetMapping("/accessDenied")
	public String showAccessDenied() {
		
		return "accessDenied";	
	}	
}