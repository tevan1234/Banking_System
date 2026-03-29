package com.example.banking.mapper;

import java.util.List;

import com.example.banking.model.entity.Employee;

public interface EmployeeMapper {

	int insertEmployee(Employee employee);
	
	List<Employee> selectAllEmployee();
	
	Employee selectByUserId(long userId);
	
}
