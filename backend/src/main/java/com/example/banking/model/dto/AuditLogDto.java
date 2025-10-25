package com.example.banking.model.dto;

import lombok.Data;

@Data
public class AuditLogDto {
	private long userId;
	private String accountNo;
	private String action;
	private String ipAddress;
}
