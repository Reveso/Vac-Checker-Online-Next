package com.lukasrosz.vaccheckeronline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lukasrosz.vaccheckeronline.accounts.entity.User;
import com.lukasrosz.vaccheckeronline.accounts.entity.UserDto;
import com.lukasrosz.vaccheckeronline.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserDetailsManager userDetailManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login-form";
	}

	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied";
	}

	@GetMapping("/showRegistrationFrom")
	public String showRegistrationForm(Model model) {
		model.addAttribute("newUser", new UserDto());

		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String registerUserAccount(@ModelAttribute("newUser") @Valid UserDto newUser, 
			BindingResult bindingResult) {
		
		User registered = new User();
		
		if(!bindingResult.hasErrors()) {
			registered = 
		}
		
		return null;
	}
	
	private User createUserAccount(UserDto userDto, BindingResult bindingResult) {
		User registered = null;
		try {
			registered = userService.
		}
	}
	
//	@PostMapping("/processRegistrationForm")
//	public String processRegistrationForm(@Valid @ModelAttribute("newUser") UserDto newUser, 
//									BindingResult bindingResult, Model model) {
//		// form validation
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("registrationError", "User name/password cannot be empty");
//
//			return "registration-form";
//		}
//
//		// check the database if user already exists
//		if (userExists(newUser)) {
//			model.addAttribute("registrationError", "User already exists");
//			return "registration-form";
//		}
//
//		// encrypt the password
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(newUser.getPassword());
//
//		// prepend the encoding algorithm id
//		encodedPassword = "{bcrypt}" + encodedPassword;
//
//		// give user default role of employee
//		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_UNREGISTERED");
//		
//		// create user details object
//		org.springframework.security.core.userdetails
//		.User tempUser = new org.springframework.security.core.userdetails
//						.User(newUser.getUsername(), encodedPassword, authorities);
//		
//		
//		// save user in the database
//		userDetailManager.createUser(tempUser);
//		
//		return "registration-confirmation";
//	}
	
	private boolean userExists(UserDto user) {
		return userDetailManager.userExists(user.getUsername());
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
