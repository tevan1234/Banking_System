package com.example.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.mapper.EmployeeMapper;
import com.example.banking.mapperstruct.EmployeeMapperStruct;
import com.example.banking.model.dto.EmployeeDto;
import com.example.banking.model.entity.Employee;
import com.example.banking.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee employee = Employee.builder().userId(employeeDto.getUserId()).idNumber(employeeDto.getIdNumber())
				.branch(employeeDto.getBranch()).role(employeeDto.getRole()).status(employeeDto.getStatus()).build();
		if (employeeMapper.insertEmployee(employee) != 1) {
			throw new RuntimeException("員工建立失敗");
		}
		EmployeeDto result = EmployeeMapperStruct.INSTANCE.toDto(employee);
		return result;
	}

	@Override
	public List<EmployeeDto> listEmployees() {
		List<Employee> employees = employeeMapper.selectAllEmployee();
		List<EmployeeDto> employeeDtos = EmployeeMapperStruct.INSTANCE.toDtoList(employees);
		return employeeDtos;
	}

	@Override
	public EmployeeDto queryByUserId(long userId) {
		Employee employee = employeeMapper.selectByUserId(userId);
		EmployeeDto employeeDto = EmployeeMapperStruct.INSTANCE.toDto(employee);
		return employeeDto;
	}

}
