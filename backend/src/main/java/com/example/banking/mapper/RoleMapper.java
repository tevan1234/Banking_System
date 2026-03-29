package com.example.banking.mapper;

import java.util.List;

public interface RoleMapper {

	List<String> getRolesByUserId(Long userId);
	
}
