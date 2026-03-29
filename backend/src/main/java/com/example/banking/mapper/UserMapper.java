package com.example.banking.mapper;

import java.util.List;

import com.example.banking.model.dto.UserDetailDto;
import com.example.banking.model.dto.UserProfileDto;
import com.example.banking.model.entity.User;

public interface UserMapper {

	int insertUser(User user);
	
	List<User> selectAllUser();

	User selectById(long userId);
	
	User selectByName(String Name);
	
	UserDetailDto selectUserDetailById(long userId);
	
	UserProfileDto selectUserProfileById(long userId);
	
	int updateUserProfile(UserProfileDto userProfileDto);
}
