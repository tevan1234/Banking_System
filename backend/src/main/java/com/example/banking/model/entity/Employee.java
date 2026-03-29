package com.example.banking.model.entity;

import java.security.Timestamp;

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
public class Employee {
	private long employeeId;
	private long userId;
	private String idNumber;
	private String branch;
	private EmployeeRole role;
	private EmployeeStatus status;
	private Timestamp createAt;
}
