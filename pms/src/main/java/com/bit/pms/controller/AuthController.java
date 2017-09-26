package com.bit.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bit.pms.dto.GeneralResult;
import com.bit.pms.dto.JwtAuthenticationResponse;
import com.bit.pms.enums.ResultStatus;
import com.bit.pms.model.User;
import com.bit.pms.service.AuthService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User authenticationRequest) {
		final String token = authService.login(authenticationRequest.getEmail(), authenticationRequest.getPwd());

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
	public ResponseEntity<GeneralResult> register(@RequestBody User addedUser) {
		User user = authService.register(addedUser);
		if (user == null) {
			return ResponseEntity.ok(new GeneralResult(ResultStatus.FAIL.name()));
		} else {
			return ResponseEntity.ok(new GeneralResult(ResultStatus.SUCCESS.name()));
		}

	}

}
