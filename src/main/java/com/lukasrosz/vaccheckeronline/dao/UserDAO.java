package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import com.lukasrosz.vaccheckeronline.accounts.entity.User;


public interface UserDAO {

	public List<User> getUsersList();

	public User getUser(String username);

	public void saveUser(User user);
}
