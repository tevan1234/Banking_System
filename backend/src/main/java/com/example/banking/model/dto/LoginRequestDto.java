package com.example.banking.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

	private String userName;
	private String password;
	
	public LoginRequestDto(String userName,String password) {
		this.userName = userName;
		this.password = password;
	}
	
}
