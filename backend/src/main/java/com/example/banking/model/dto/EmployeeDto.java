package com.example.banking.model.dto;

import com.example.banking.model.enums.EmployeeRole;
import com.example.banking.model.enums.EmployeeStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
	private long employeeId;
	private long userId;
	private String idNumber;
	private String branch;
	private EmployeeRole role;
	private EmployeeStatus status;
}
