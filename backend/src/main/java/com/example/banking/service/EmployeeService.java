package com.example.banking.service;

import java.util.List;

import com.example.banking.model.dto.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto employeeDto);
	
	public List<EmployeeDto> listEmployees();
	
	public EmployeeDto queryByUserId(long userId);
	
}
