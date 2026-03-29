package com.example.banking.mapper;

import java.util.List;

import com.example.banking.model.entity.Customer;

public interface CustomerMapper {
	
	public List<Customer> selectAllCustomer();
	
	public Customer selectById(long customerId);

	public Customer selectByUserId(long userId);

	public int insertCustomer(Customer customer);
	
}
