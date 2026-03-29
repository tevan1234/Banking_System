package com.example.banking.model.dto;

import java.util.List;

import com.example.banking.model.enums.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class UserDetailDto extends UserDto {
	
	private Customer customer;
	@Data
	public static class Customer{
		private long customerId;
		private long userId;
		private String idNumber;
		private String dob;
		private String address;
		private String branch;
		private KycStatus kycStatus;
		private List<Account> accounts;
	}
	@Data
	public static class Account{
		private long accountId;
		private long customerId;
	}
}
