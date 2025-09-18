package com.example.banking.model.dto;

import com.example.banking.model.enums.UserStatus;

public class UserCert {
	private final Long userId;
	private final String userName;
	private final String passwordHash;
	private final UserStatus status;
	
	public UserCert(Long userId,String userName,String passwordHash,UserStatus status) {
		this.userId = userId;
		this.userName= userName;
		this.passwordHash = passwordHash;
		this.status = status;
	}
}
