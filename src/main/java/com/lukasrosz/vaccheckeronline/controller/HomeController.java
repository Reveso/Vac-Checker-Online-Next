package com.lukasrosz.vaccheckeronline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lukasrosz.vaccheckeronline.accounts.entity.User;
import com.lukasrosz.vaccheckeronline.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
		
	@GetMapping("/")
	public String showStartingPage(Model model) {		
		System.out.println("Kek");
		return "index";
	}

	@GetMapping("/test")
	public String test() {
		
		List<User> users = userService.getUsersList();
		
		users.forEach(System.out::println);
		return "index";
	}
	
}
