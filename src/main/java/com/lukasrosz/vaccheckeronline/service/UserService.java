package com.lukasrosz.vaccheckeronline.service;

import java.util.List;

import com.lukasrosz.vaccheckeronline.accounts.entity.UserDto;

public interface UserService {
	
	public List<UserDto> getUsersList();

	public UserDto getUser(String username);

	public void saveUser(UserDto user);

}
