package com.example.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.mapper.UserMapper;
import com.example.banking.mapperstruct.UserMapperStruct;
import com.example.banking.model.dto.CreateUserDto;
import com.example.banking.model.dto.UserDetailDto;
import com.example.banking.model.dto.UserDto;
import com.example.banking.model.dto.UserProfileDto;
import com.example.banking.model.entity.User;
import com.example.banking.model.enums.UserStatus;
import com.example.banking.service.UserService;
import com.example.banking.utils.Hash;
import com.example.banking.utils.MaskUtils;

import io.micrometer.common.util.StringUtils;

@Service
public class UserSereviceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto addUser(CreateUserDto userDto) {

		if (userMapper.selectByName(userDto.getUserName()) != null) {
			throw new RuntimeException("用戶名已存在");
		}
		User user = User.builder().userName(userDto.getUserName()).fullName(userDto.getFullName())
				.phone(userDto.getPhone()).email(userDto.getEmail())
				.passwordHash(Hash.encodePassword(userDto.getPassword()))
				.status(userDto.getEmail() != null ? UserStatus.ACTIVE : UserStatus.SUSPENDED).build();
		if (userMapper.insertUser(user) != 1) {
			throw new RuntimeException("使用者建立失敗");
		}

		UserDto result = UserMapperStruct.INSTANCE.toDto(user);
		return result;
	}

	@Override
	public List<UserDto> listUsers() {
		List<User> users = userMapper.selectAllUser();
		List<UserDto> userDtos = UserMapperStruct.INSTANCE.toDtoList(users);//entity轉dto
		return userDtos;
	}

	@Override
	public UserDto queryById(long userId) {
		User user = userMapper.selectById(userId);
		UserDto userDto = UserDto.builder().userId(userId).userName(user.getUserName()).fullName(user.getFullName())
				.email(user.getEmail()).phone(user.getPhone()).status(user.getStatus()).build();
		return userDto;
	}

	@Override
	public UserDto queryByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetailDto getUserDetail(long userId) {
		UserDetailDto userDetail = userMapper.selectUserDetailById(userId);
		String email = userDetail.getEmail();
		String phone = userDetail.getPhone();

		userDetail.setEmail(MaskUtils.maskEmail(email));
		userDetail.setPhone(MaskUtils.maskPhone(phone));
		if (userDetail.getCustomer() != null) {
			String idNumber = userDetail.getCustomer().getIdNumber();
			String address = userDetail.getCustomer().getAddress();
			userDetail.getCustomer().setIdNumber(MaskUtils.maskIdNumber(idNumber));
			userDetail.getCustomer().setAddress(MaskUtils.maskAddress(address));
		}
		return userDetail;
	}

	@Override
	public UserProfileDto updateUserProfile(UserProfileDto userProfileDto) {
		if (StringUtils.isEmpty(userProfileDto.getEmail()) && StringUtils.isEmpty(userProfileDto.getPhone())) {
			throw new IllegalArgumentException("phone 或 email 至少填一個");
		}
		if (userMapper.updateUserProfile(userProfileDto) == 1) {
	        return userMapper.selectUserProfileById(userProfileDto.getUserId());
	    }
		throw new RuntimeException("更新失敗");
	}

}
