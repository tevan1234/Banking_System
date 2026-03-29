package com.example.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.mapper.CustomerMapper;
import com.example.banking.mapperstruct.CustomerMapperStruct;
import com.example.banking.model.dto.CustomerApplyDto;
import com.example.banking.model.dto.CustomerDto;
import com.example.banking.model.entity.Customer;
import com.example.banking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		if (customerMapper.selectByUserId(customerDto.getUserId()) != null) {
			throw new RuntimeException("客戶帳號已存在");
		}
		Customer customer = Customer.builder().userId(customerDto.getUserId()).idNumber(customerDto.getIdNumber())
				.dob(customerDto.getDob()).address(customerDto.getAddress()).branch(customerDto.getBranch()).build();
		if (customerMapper.insertCustomer(customer) != 1) {
			throw new RuntimeException("建立顧客資訊失敗");
		}

		CustomerDto result = CustomerMapperStruct.INSTANCE.toDto(customer);
		return result;
	}

	@Override
	public CustomerDto applyCustomer(long userId, CustomerApplyDto customerApplyDto) {
		Customer customer = Customer.builder().userId(userId)
				.idNumber(customerApplyDto.getIdNumber()).dob(customerApplyDto.getDob())
				.address(customerApplyDto.getAddress()).branch(customerApplyDto.getBranch()).build();
		if (customerMapper.insertCustomer(customer) != 1) {
			throw new RuntimeException("建立顧客資訊失敗");
		}
		CustomerDto result = CustomerMapperStruct.INSTANCE.toDto(customer);
		return result;
	}

	@Override
	public List<CustomerDto> listCutomers() {
		List<Customer> customers = customerMapper.selectAllCustomer();
		List<CustomerDto> customerDtos = CustomerMapperStruct.INSTANCE.toDtoList(customers);
		return customerDtos;
	}

	@Override
	public CustomerDto queryByUserId(long userId) {
		if (customerMapper.selectByUserId(userId) == null) {
			throw new RuntimeException("查無客戶資訊");
		}
		Customer customer = customerMapper.selectByUserId(userId);
		CustomerDto customerDto = CustomerMapperStruct.INSTANCE.toDto(customer);
		return customerDto;
	}

	@Override
	public CustomerDto queryById(long customerId) {
		if (customerMapper.selectById(customerId) == null) {
			throw new RuntimeException("查無客戶資訊");
		}
		Customer customer = customerMapper.selectById(customerId);
		CustomerDto customerDto = CustomerMapperStruct.INSTANCE.toDto(customer);
		return customerDto;
	}

}
