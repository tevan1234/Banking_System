package com.example.banking.service;

import java.time.LocalDateTime;

public interface TokenBlacklistService {
	
	public void addToBlacklist(String jti,Long userId, LocalDateTime expiredAt);
	
	public boolean isBlacklisted(String jti);
}
