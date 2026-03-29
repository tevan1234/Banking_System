package com.example.banking.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Hash {

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static String encodePassword(String rawPasseord) {
		return passwordEncoder.encode(rawPasseord);
	}
	
	public static boolean matches(String rawPassword,String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
