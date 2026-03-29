package com.example.banking.model.dto;

import lombok.Data;

@Data
public class CreateUserDto {
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
}
