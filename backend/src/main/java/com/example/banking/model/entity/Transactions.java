package com.example.banking.model.entity;

import java.math.BigDecimal;
import java.security.Timestamp;

import com.example.banking.model.enums.TransactionType;

import lombok.Data;

@Data
public class Transactions {
	private long txnId;
	private long accountId;
	private TransactionType txnType;
	private BigDecimal amount;
	private BigDecimal balanceAfter;//交易完成後的帳戶餘額
	private String reference;//交易的參考編號或備註
	private Timestamp createdAt;
}
