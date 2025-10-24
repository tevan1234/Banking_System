package com.example.banking.model.entity;

import java.time.LocalDateTime;

import com.example.banking.model.enums.UserStatus;

import lombok.Data;

@Data
public class User {
	private Long userId;
	private String userName;
	private String passwordHash;
	private String fullName;
	private String email;
	private String phone;
	private UserStatus status;
	private LocalDateTime createdTime;
}
