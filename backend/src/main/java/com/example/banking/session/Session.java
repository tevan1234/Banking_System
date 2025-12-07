package com.example.banking.session;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.banking.model.entity.User;

public interface Session {
	
	public SessionManager getSessionManager();
	
	public String getUserName();
	
	public long getUserId();
	
	public JdbcTemplate getJdbcTemplate();
	
	public User getUserInfo();
	
	List<String> getRoles();
	
	public boolean isAdmin();
	
	void disConnect();
	
}
