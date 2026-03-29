package com.example.banking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
	private long userId;
	private String fullName;
	private String email;
	private String phone;
	private String dob;
	private String address;
}
