package com.example.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.dto.ApiResponse;
import com.example.banking.model.dto.CreateUserDto;
import com.example.banking.model.dto.UserDetailDto;
import com.example.banking.model.dto.UserDto;
import com.example.banking.model.dto.UserProfileDto;
import com.example.banking.service.UserService;



@RestController
@RequestMapping("/api/User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/listAll")
	public ResponseEntity<ApiResponse<List<UserDto>>> listUsers() {
		try {
			List<UserDto> userList = userService.listUsers();
			return ResponseEntity.ok(ApiResponse.success("使用者列表查詢成功", userList));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("使用者列表查詢失敗：" + e.getMessage()));
		}
	}
	
	@GetMapping("/getByUserId")
	public ResponseEntity<ApiResponse<UserDto>> getByUserId(@RequestParam long userId) {
		try {
			UserDto user = userService.queryById(userId);
			return ResponseEntity.ok(ApiResponse.success("使用者查詢成功", user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("使用者查詢失敗或不存在：" + e.getMessage()));
		}
	}

	
	@GetMapping("/getByUserName")
	public ResponseEntity<ApiResponse<UserDto>> getByUserName(@RequestParam String userName) {
		try {
			UserDto user = userService.queryByUserName(userName);
			return ResponseEntity.ok(ApiResponse.success("使用者查詢成功", user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("使用者查詢失敗或不存在：" + e.getMessage()));
		}
	}
	
	@GetMapping("/detail")
	public ResponseEntity<ApiResponse<UserDetailDto>> getUserDetail(@RequestParam long userId) {
		try {
			UserDetailDto user = userService.getUserDetail(userId);
			return ResponseEntity.ok(ApiResponse.success("用戶資訊查詢成功", user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("用戶資訊查詢失敗或不存在：" + e.getMessage()));
		}
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody CreateUserDto user) {
		try {
			UserDto newUser = userService.addUser(user);
			return ResponseEntity.ok(ApiResponse.success("使用者建立成功", newUser));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("建立失敗：" + e.getMessage()));
		}
	}
	
	@PutMapping("/updateProfile")
	public ResponseEntity<ApiResponse<UserProfileDto>> updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
		try {
			UserProfileDto newProfile = userService.updateUserProfile(userProfileDto);
			return ResponseEntity.ok(ApiResponse.success("用戶資料更新成功", newProfile));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("更新客戶資料失敗:"+e.getMessage()));
		}
	}
	
//	@PutMapping("/updatePassword")
//	public String putMethodName(@PathVariable String id, @RequestBody String entity) {
//		//TODO: process PUT request
//		
//		return entity;
//	}
}
