package com.example.banking.model.dto;

import lombok.Data;

@Data
public class UserDto {
	private String userName;
	private String passwordHash;
	private String fullName;
	private String email;
	private String phone;
}
