package com.example.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller("MyBank/home")
public class HomeController {

	@GetMapping
	public String home() {
		return "redirect:/index.html";
	}
	
}
