package com.example.banking.model.entity;

import java.math.BigDecimal;
import java.security.Timestamp;

import com.example.banking.model.enums.TransferStatus;

import lombok.Data;

@Data
public class Transfer {
	private long transferId;
	private long fromAccount;
	private long toAccount;
	private BigDecimal amount;
	private Timestamp createdAt;
	private TransferStatus status;
}
