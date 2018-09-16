package com.lukasrosz.vaccheckeronline.dao;

import java.util.List;

import com.lukasrosz.vaccheckeronline.accounts.entity.UserDto;


public interface UserDAO {

	public List<UserDto> getUsersList();

	public UserDto getUser(String username);

	public void saveUser(UserDto user);
}
