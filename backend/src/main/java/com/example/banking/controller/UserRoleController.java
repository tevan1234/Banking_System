package com.example.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.dto.ApiResponse;
import com.example.banking.model.entity.UserRole;
import com.example.banking.service.UserRoleService;

@RestController
@RequestMapping("/api/UserRole")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	@PostMapping("/addUserRole")
	public ResponseEntity<ApiResponse<UserRole>> addUserRole(@RequestBody UserRole userRole){
		try {
			UserRole newUserRole = userRoleService.addUserRole(userRole);
			return ResponseEntity.ok(ApiResponse.success("使用者權限建立成功", newUserRole));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("建立失敗:" + e.getMessage()));
		}
	}
	
}
