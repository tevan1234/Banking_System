package com.example.banking.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration-time-seconds}")
	private long EXPIRATION_TIME_SECONDS;
	private final Algorithm ALGORITHM;
	
	public JwtService(@Value("${jwt.secret}") String secret) {
        this.ALGORITHM = Algorithm.HMAC256(secret);
    }

	public String generateToken(long userId, List<String> roles) {
		Instant now = Instant.now();
	    Instant expiration = now.plusSeconds(EXPIRATION_TIME_SECONDS);
		return JWT.create().withSubject(String.valueOf(userId)).withJWTId(UUID.randomUUID().toString())
				.withClaim("roles", roles).withIssuedAt(Date.from(now)).withExpiresAt(Date.from(expiration)).sign(ALGORITHM);
	}

	// 驗證並解析 token
	public DecodedJWT verifyToken(String token) {
		return JWT.require(ALGORITHM).build().verify(token);
	}

	public Long getUserId(String token) {
		try {
			DecodedJWT jwt = verifyToken(token);
			return Long.parseLong(jwt.getSubject());
		} catch (Exception e) {
			return null;
		}
	}

	public String getJti(String token) {
		try {
			DecodedJWT jwt = verifyToken(token);
			return jwt.getId();
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> getRoles(String token) {
		try {
			DecodedJWT jwt = verifyToken(token);
			return jwt.getClaim("roles").asList(String.class);
		} catch (Exception e) {
			return null;
		}
	}

	public Date getExpiration(String token) {
		try {
			DecodedJWT jwt = verifyToken(token);
			return jwt.getExpiresAt();
		} catch (Exception e) {
			return null;
		}
	}

	public long getExpirationTime(String token) {
		try {
			Date expiration = getExpiration(token);
			if (expiration == null) {
				return 0;
			}
			Date now = new Date();
			long seconds = (expiration.getTime() - now.getTime())/1000;
			return Math.max(0, seconds);
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isTokenExpired(String token) {
		try {
			Date expiration = getExpiration(token);
			return expiration != null && expiration.before(new Date());
		} catch (Exception e) {
			return true;
		}
	}

	public boolean validateToken(String token) {
		try {
	        verifyToken(token);
	        return true;
	    } catch (JWTVerificationException e) {
	        return false;
	    }
	}

	public boolean validateToken(String token, Long userId) {
		try {
			DecodedJWT jwt = verifyToken(token);
			return jwt.getSubject().equals(String.valueOf(userId));
		} catch (JWTVerificationException  e) {
			return false;
		}
	}

}
