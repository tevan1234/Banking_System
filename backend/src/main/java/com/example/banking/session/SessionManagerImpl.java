package com.example.banking.session;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.banking.mapper.RoleMapper;
import com.example.banking.model.entity.Users;

public class SessionManagerImpl implements SessionManager {
	
	private Map<String, Session> sessionMap = new ConcurrentHashMap<>();

	@Override
	public Session createSession(Users user, JdbcTemplate jdbcTemplate, RoleMapper roleMapper) {
		List<String> roles = roleMapper.getRolesByUserId(user.getUserId());
		Session session = new SessionImpl(this, user, jdbcTemplate, roles);
		sessionMap.put(user.getUserName(), session);
		return session;
	}

	@Override
	public Session getSession(String userName) {
		return sessionMap.get(userName);
	}

	@Override
	public void removeSession(String userName) {
		sessionMap.remove(userName);
	}

}
