package com.example.banking.session;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.banking.model.entity.Users;

public interface Session {
	
	public SessionManager getSessionManager();
	
	public String getUserName();
	
	public long getUserId();
	
	public JdbcTemplate getJdbcTemplate();
	
	public Users getUserInfo();
	
	List<String> getRoles();
	
	public boolean isAdmin();
	
	void disConnect();
	
}
