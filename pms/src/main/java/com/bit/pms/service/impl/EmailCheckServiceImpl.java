package com.bit.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.pms.repository.UserRepository;
import com.bit.pms.service.EmailCheckService;

@Service
public class EmailCheckServiceImpl implements EmailCheckService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValidEmail(String email) {
		if (userRepository.findByEmail(email) != null) {
			return true;
		}
		return false;
	}

}
