package com.lukasrosz.vaccheckeronline.service;

import java.util.List;

import com.lukasrosz.vaccheckeronline.accounts.entity.User;

public interface UserService {
	
	public List<User> getUsersList();

	public User getUser(String username);

	public void saveUser(User user);
	
	
	public User registerNewUserAccount();
	
	

}
