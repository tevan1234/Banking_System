package com.example.banking.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionDto {
	private long accountId;
	private String txnType;
	private BigDecimal amount;
	private BigDecimal balanceAfter;
	private String reference;
}
