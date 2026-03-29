package com.example.banking.mapper;

import com.example.banking.model.entity.TokenBlacklist;

public interface TokenBlacklistMapper {
	
	TokenBlacklist selectByJti(String jti);
	
	int insertBlacklist(TokenBlacklist tokenBlacklist);
	
	boolean existsByJti(String jti);
	
	int deleteExpired();
}
