package com.lukasrosz.vaccheckeronline.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.lukasrosz.vaccheckeronline.accounts.entity.Role;
import com.lukasrosz.vaccheckeronline.accounts.entity.AuthorityContsants;
import com.lukasrosz.vaccheckeronline.accounts.entity.User;
import com.lukasrosz.vaccheckeronline.accounts.entity.UserForm;
import com.lukasrosz.vaccheckeronline.service.UserService;



@Controller
@RequestMapping("/adminPanel")
public class AdminController {
	
	@Autowired
	private UserDetailsManager userDetailManager;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/showPanel")
	public String showPanel(Model model) {

		List<User> users = userService.getUsersList();
		UserForm userForm = new UserForm();
		userForm.setUsers(users);
		
		model.addAttribute("userForm", userForm);

		return "admin-panel";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam("username") String username, Model model) {
	
		User user = userService.getUser(username);
		
		List<Role> authorityList = new ArrayList<>();
		
		for (String authority : AuthorityContsants.authorities) {
			authorityList.add(new Role(username, "ROLE_" + authority));
		}
		
		model.addAttribute("user", user);
		model.addAttribute("authorityList", authorityList);
		return "edit-user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "edit-user-form";
		}
		
		System.out.println("===>>>" + user);
		System.out.println(user.getAuthorities());
		
		if(user.getAuthorities() == null) {
			user.setAuthorities(new ArrayList<Role>());
			user.getAuthorities().add(new Role("ROLE_UNREGISTERED"));
			
		} else if(user.getAuthorities().size() == 0) {
			user.getAuthorities().add(new Role("ROLE_UNREGISTERED"));	
		}
					
		boolean isEnabled = user.getEnabled();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		user.getAuthorities().forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth.getAuthority())));
		
		org.springframework.security.core.userdetails
		.User tempUser = new org.springframework.security.core.userdetails
						.User(user.getUsername(), user.getPassword(), isEnabled, true, true, true, authorities);
		
		userDetailManager.updateUser(tempUser);
		
		return "redirect:/adminPanel/showPanel";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

}
