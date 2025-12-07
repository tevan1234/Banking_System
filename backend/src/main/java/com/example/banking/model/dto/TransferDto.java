package com.example.banking.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferDto {
	private long fromAccount;
	private long toAccount;
	private BigDecimal amount;
}
