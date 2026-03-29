package com.example.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.exception.CertException;
import com.example.banking.exception.UserNotFoundException;
import com.example.banking.mapper.RoleMapper;
import com.example.banking.mapper.UserMapper;
import com.example.banking.model.dto.UserCertDto;
import com.example.banking.model.entity.User;
import com.example.banking.service.CertService;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public UserCertDto getCert(String userName, String password) throws CertException {
		User user = userMapper.selectByName(userName);
		if (user == null) {
			throw new UserNotFoundException();
		}
		long userId = user.getUserId();
		List<String> userRoles = roleMapper.getRolesByUserId(userId);
		UserCertDto userCert = new UserCertDto(user.getUserId(), user.getUserName(), user.getFullName(), user.getEmail(), user.getPhone(), userRoles, user.getStatus());
		return userCert;
	}

}
