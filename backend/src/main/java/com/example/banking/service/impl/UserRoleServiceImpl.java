package com.example.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.mapper.UserRoleMapper;
import com.example.banking.model.entity.UserRole;
import com.example.banking.service.UserRoleService;

import io.micrometer.common.util.StringUtils;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public UserRole addUserRole(UserRole userRole) {
		long userId = userRole.getUserId();
		long roleId = userRole.getRoleId();
		String rid = String.valueOf(roleId);
		if (StringUtils.isEmpty(rid)) {
			roleId = 4;
		}
		int rows = userRoleMapper.insertUserRole(userId, roleId);
		if (rows == 1) {
			return userRole;
		}
		throw new RuntimeException("使用者權限創建失敗");
	}

}
