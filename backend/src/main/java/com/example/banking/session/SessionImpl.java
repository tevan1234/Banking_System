package com.example.banking.session;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.banking.model.entity.User;

public class SessionImpl implements Session{

	private final SessionManager sessionManager;
	private final String userName;
	private final Long userId;
	private final User user;
	private final JdbcTemplate jdbcTemplate;
    private final List<String> roles;
	
	public SessionImpl(SessionManager manager, User user, JdbcTemplate jdbcTemplate, List<String> roles) {
        this.sessionManager = manager;
        this.user = user;
        this.userName = user.getUserName();
        this.userId = user.getUserId();
        this.jdbcTemplate = jdbcTemplate;
        this.roles = roles;
    }
	
	@Override
	public SessionManager getSessionManager() {
		return sessionManager;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public User getUserInfo() {
		return user;
	}
	
	@Override
	public List<String> getRoles() {
		return roles;
	}

	@Override
	public boolean isAdmin() {
		return roles.contains("ADMIN");
	}

	@Override
	public void disConnect() {
		sessionManager.removeSession(userName);
	}
	
}
