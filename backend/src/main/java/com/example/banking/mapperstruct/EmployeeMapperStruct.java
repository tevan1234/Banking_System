package com.example.banking.mapperstruct;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.banking.model.dto.EmployeeDto;
import com.example.banking.model.entity.Employee;

@Mapper
public interface EmployeeMapperStruct {
	EmployeeMapperStruct INSTANCE = Mappers.getMapper(EmployeeMapperStruct.class);
	EmployeeDto toDto(Employee employee);
	List<EmployeeDto> toDtoList(List<Employee> employees);
}
