package com.example.banking.model.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TokenBlacklist {
	private long tokenId;
	private String jti;//jwt-id
	private long userId;
	private LocalDateTime expiredAt;
	private LocalDateTime createdAt;
}
