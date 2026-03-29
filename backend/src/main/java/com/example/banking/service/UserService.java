package com.example.banking.service;

import java.util.List;

import com.example.banking.model.dto.CreateUserDto;
import com.example.banking.model.dto.UserDetailDto;
import com.example.banking.model.dto.UserDto;
import com.example.banking.model.dto.UserProfileDto;

public interface UserService {
	
	public UserDto addUser(CreateUserDto user);
	
	public List<UserDto> listUsers();

	public UserDto queryById(long userId);
	
	public UserDto queryByUserName(String userName);
	
	public UserDetailDto getUserDetail(long userId);
	
	public UserProfileDto updateUserProfile(UserProfileDto userProfileDto);
}
