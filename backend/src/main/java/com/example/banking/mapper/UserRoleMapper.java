package com.example.banking.mapper;

import java.util.List;

public interface UserRoleMapper {

	List<Long> selectById(long userId);
	
	int insertUserRole(long userId,long roleId);
	
	int updateUserRole(long userId,long roleId);
	
}
