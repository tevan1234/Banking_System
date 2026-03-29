package com.example.banking.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.mapper.TokenBlacklistMapper;
import com.example.banking.model.entity.TokenBlacklist;
import com.example.banking.service.TokenBlacklistService;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService{

	@Autowired
	private TokenBlacklistMapper mapper;
	
	@Override
	public void addToBlacklist(String jti,Long userId, LocalDateTime expiredAt) {
		TokenBlacklist blacklist = new TokenBlacklist();
		blacklist.setJti(jti);
		blacklist.setUserId(userId);
		blacklist.setExpiredAt(expiredAt);
		mapper.insertBlacklist(blacklist);
	}

	@Override
	public boolean isBlacklisted(String jti) {
		return mapper.existsByJti(jti);
	}
	
	/**
     * 定期清理過期記錄（每天凌晨 2 點）
     *
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanExpiredTokens() {
        int deleted = mapper.deleteExpired();
        System.out.println("清理了 " + deleted + " 筆過期的黑名單記錄");
    }*/

}
