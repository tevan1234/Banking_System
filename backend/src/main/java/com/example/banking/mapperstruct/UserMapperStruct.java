package com.example.banking.mapperstruct;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.banking.model.dto.UserDto;
import com.example.banking.model.entity.User;

@Mapper
public interface UserMapperStruct {
    UserMapperStruct INSTANCE = Mappers.getMapper(UserMapperStruct.class);
    UserDto toDto(User user);
    List<UserDto> toDtoList(List<User> users);
}