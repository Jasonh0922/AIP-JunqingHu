package com.bit.pms.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bit.pms.constants.UserRole;
import com.bit.pms.model.User;
import com.bit.pms.repository.UserRepository;
import com.bit.pms.service.AuthService;
import com.bit.pms.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User userToAdd) {
		final String username = userToAdd.getEmail();
		if (userRepository.findByEmail(username) != null) {
			return null;
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = userToAdd.getPwd();
		userToAdd.setPwd(encoder.encode(rawPassword));
		userToAdd.setRoles(Arrays.asList(UserRole.ROLE_USER.name()));
		return userRepository.save(userToAdd);
	}

	@Override
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = jwtUtil.generateToken(userDetails);
		return token;
	}

}
