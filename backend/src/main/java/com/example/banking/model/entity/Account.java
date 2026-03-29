package com.example.banking.model.entity;

import java.math.BigDecimal;
import java.security.Timestamp;

import com.example.banking.model.enums.AccountStatus;
import com.example.banking.model.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	private long accountId;
	private long customerId;
	private String accountNo;
	private AccountType accountType;
	private String branch;
	private BigDecimal balance;//餘額
	private char currency;//貨幣類型
	private AccountStatus status;
	private Timestamp createdAt;
}
