package com.example.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.dto.ApiResponse;
import com.example.banking.model.dto.CustomerApplyDto;
import com.example.banking.model.dto.CustomerDto;
import com.example.banking.service.CustomerService;
import com.example.banking.service.UserService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/Customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/listAll")
	public ResponseEntity<ApiResponse<List<CustomerDto>>> listCustomers() {
		try {
			List<CustomerDto> customerList = customerService.listCutomers();
			return ResponseEntity.ok(ApiResponse.success("客戶列表查詢成功", customerList));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("客戶列表查詢失敗：" + e.getMessage()));
		}
	}
	
	@PermitAll
	@PostMapping("/addCustomer")
	public ResponseEntity<ApiResponse<CustomerDto>> addCustomer(@RequestBody CustomerDto customerDto) {
		try {
			CustomerDto customer = customerService.addCustomer(customerDto);
			return ResponseEntity.ok(ApiResponse.success("客戶建立成功", customer));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("建立失敗：" + e.getMessage()));
		}
	}
	
	@PostMapping("/applyCustomer")
	public ResponseEntity<ApiResponse<CustomerDto>> applyCustomer(@RequestBody CustomerApplyDto customerApplyDto) {
		long userId = (long)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			if (userService.queryById(userId) == null) {
				throw new RuntimeException("使用者不存在");
			}
			CustomerDto newCustomer = customerService.applyCustomer(userId, customerApplyDto);
			
			return ResponseEntity.ok(ApiResponse.success("客戶建立成功", newCustomer));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ApiResponse.failure("客戶建立失敗：" + e.getMessage()));
		}
	}
	
}
