package com.example.banking.model.entity;

import java.time.LocalDate;

import com.example.banking.model.enums.KycStatus;

import lombok.Data;

@Data
public class Customers {
	private long customerId;
	private long userId;
	private LocalDate dob;
	private String address;
	private KycStatus kycStatus;//了解你的客戶狀態
}
