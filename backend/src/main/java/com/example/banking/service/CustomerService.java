package com.example.banking.service;

import java.util.List;

import com.example.banking.model.dto.CustomerApplyDto;
import com.example.banking.model.dto.CustomerDto;

public interface CustomerService {

	public CustomerDto addCustomer(CustomerDto customer);
	
	public CustomerDto applyCustomer(long userId, CustomerApplyDto customerApplyDto);

	public List<CustomerDto> listCutomers();
	
	public CustomerDto queryByUserId(long userId);
	
	public CustomerDto queryById(long customerId);
}
