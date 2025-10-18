package com.example.banking.session;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.banking.mapper.RoleMapper;
import com.example.banking.model.entity.Users;

public interface SessionManager {

	public Session createSession(Users user,JdbcTemplate jdbcTemplate,RoleMapper roleMapper);
	
	public Session getSession(String userName);
	
	public void removeSession(String userName);
	
}
