package com.bit.pms.service;

import com.bit.pms.model.User;

public interface AuthService {
	User register(User user);
	String login(String username, String password);
}
