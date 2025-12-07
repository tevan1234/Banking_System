package com.example.banking.model.dto;

import lombok.Data;

@Data
public class CustomerDto {
	private long userId;
	private String dob;
	private String address;
	private String kycStatus;
}
