package com.example.banking.model.dto;

import java.util.List;

import com.example.banking.model.enums.UserStatus;

public class UserCertDto {
	private final Long userId;
	private final String userName;
	private final String fullName;
	private final String email;
	private final String phone;
	private final List<String> roles;
	private final UserStatus status;
	
	public UserCertDto(Long userId,String userName, String fullName, String email, String phone,List<String> roles, UserStatus status) {
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public UserStatus getStatus() {
		return status;
	}
	
	public String tostring() {
		return "UserCert [userId = " + userId + ", userName = " + userName + ", fullName = " + fullName + ", email = " + email + ", phone = " + phone + ", role = " + roles + ", status = " + status.toString();
	}
}
