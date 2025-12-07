package com.example.banking.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDto {
	private long customerId;
	private String accountNo;
	private String accountType;
	private BigDecimal balance;
	private char currency;
}
