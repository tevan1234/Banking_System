package com.example.banking.utils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.PostConstruct;

@Component
public class jwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration-time-seconds}")
	private long EXPIRATION_TIME_SECONDS;
	private Algorithm ALGORITHM;

	@PostConstruct
	public void init() {
		ALGORITHM = Algorithm.HMAC512(secret);
	}

	public String generateToken(long userId, List<String> roles) {
		Instant now = Instant.now();
		Instant expiration = now.plusSeconds(EXPIRATION_TIME_SECONDS);
		return JWT.create().withSubject(String.valueOf(userId)).withJWTId(UUID.randomUUID().toString())
				.withClaim("roles", roles).withIssuedAt(Date.from(now)).withExpiresAt(Date.from(expiration))
				.sign(ALGORITHM);
	}

	// 驗證並解析 token
	public DecodedJWT verifyToken(String token) {
		return JWT.require(ALGORITHM).build().verify(token);
	}
	
	public String getJti(String token) {
        return verifyToken(token).getId();
    }
	
	public Long getUserId(String token) {
        return Long.parseLong(verifyToken(token).getSubject());
    }
	
	public String getRole(String token) {
        return verifyToken(token).getClaim("role").asString();
    }
	
	public Date getExpiration(String token) {
        return verifyToken(token).getExpiresAt();
    }
}
