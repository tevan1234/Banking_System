package com.example.banking.model.dto;

public class LoginDto {

	private String token;
	private UserCertDto userCert;
	
	public LoginDto(String token,UserCertDto userCert) {
		this.token = token;
		this.userCert = userCert;
	}
	
	public String getToken() {
		return token;
	}
	public UserCertDto getUserCert() {
		return userCert;
	}
	
}
