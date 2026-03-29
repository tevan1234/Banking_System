package com.example.banking.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.banking.exception.CertException;
import com.example.banking.model.dto.ApiResponse;
import com.example.banking.model.dto.LoginDto;
import com.example.banking.model.dto.LoginRequestDto;
import com.example.banking.model.dto.UserCertDto;
import com.example.banking.service.CertService;
import com.example.banking.service.TokenBlacklistService;
import com.example.banking.utils.jwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	private CertService certService;
//	@Autowired
//	private JwtService jwtService;
	@Autowired
	private jwtUtil jwtUtil;
	@Autowired
	private TokenBlacklistService tokenBlacklistService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginDto>> login(@RequestBody LoginRequestDto request) {
//		UserDto user = request.getUser();
		String userName = request.getUserName();
		String password = request.getPassword();
		UserCertDto userCert = null;
		try {
			userCert = certService.getCert(userName, password);
			long userId = userCert.getUserId();
			List<String> roles = userCert.getRoles();
			String token = jwtUtil.generateToken(userId, roles);
			LoginDto loginDto = new LoginDto(token, userCert);
			return ResponseEntity.ok(ApiResponse.success("用戶登入成功!", loginDto));
		} catch (CertException e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("用戶登入失敗：" + e.getMessage()));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> postMethodName(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		try {
			DecodedJWT jwt = jwtUtil.verifyToken(token);
			String jti = jwt.getId();
			Long userId = Long.parseLong(jwt.getSubject());
			Date expiredAt = jwtUtil.getExpiration(token);
			LocalDateTime expiredTime = LocalDateTime.ofInstant(expiredAt.toInstant(), ZoneId.systemDefault());

			tokenBlacklistService.addToBlacklist(jti, userId, expiredTime);
			return ResponseEntity.ok(new ApiResponse<>(true, "登出成功", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(false, "Token 無效", null));
		}
	}

}
