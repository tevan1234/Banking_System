package com.example.banking.model.entity;

import java.security.Timestamp;

import lombok.Data;

@Data
public class AuditLog {
	private long logId;
	private long userId;
	private String action;
	private String ipAddress;
	private Timestamp createdAt;
}
