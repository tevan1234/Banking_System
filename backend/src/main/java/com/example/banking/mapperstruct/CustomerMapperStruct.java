package com.example.banking.mapperstruct;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.banking.model.dto.CustomerDto;
import com.example.banking.model.entity.Customer;

@Mapper
public interface CustomerMapperStruct {
	CustomerMapperStruct INSTANCE = Mappers.getMapper(CustomerMapperStruct.class);
	CustomerDto toDto(Customer customer);
	List<CustomerDto> toDtoList(List<Customer> customers);
}
