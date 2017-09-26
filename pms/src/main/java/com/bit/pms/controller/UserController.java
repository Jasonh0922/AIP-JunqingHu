package com.bit.pms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bit.pms.constants.ResponseString;
import com.bit.pms.enums.UserStatus;
import com.bit.pms.model.User;
import com.bit.pms.repository.UserRepository;
import com.bit.pms.service.impl.PasswordService;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER')")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Value("${aUser.name}")
	private String aName;

	@Value("${aUser.pas}")
	private String aPas;

	@Autowired
	private UserRepository userRepository;

	/**
	 * get all student from DB
	 * 
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAll() {
		return userRepository.findAllUser();
	}

	/**
	 * find user by first name
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/users/search/{key}", "/users/search/" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public List<User> search(@PathVariable Optional<String> key) {
		if (key.isPresent()) {
			return userRepository.findByFirstNameContaining(key.get());
		} else {
			return userRepository.findAllUser();
		}
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user) throws Exception {
		user.setPwd(PasswordService.getInstance().encrypt(user.getPwd()));
		User result = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
		if (result != null) {
			return ResponseEntity.ok().body(result);
		} else {
			if (user.getEmail().equals(aName) && user.getPwd().equals(aPas)) {
				return ResponseEntity.ok().body(user);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		}

	}

	/**
	 * add a student into DB with encrypted pwd
	 * 
	 * @param stu
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String addStudent(@RequestBody User stu) throws Exception {
		User user = userRepository.findByEmail(stu.getEmail());
		if (stu.getEmail().equals(aName) || user != null) {
			return ResponseString.FAIL;
		} else {
			stu.setPwd(PasswordService.getInstance().encrypt(stu.getPwd()));
			stu.setStatus(UserStatus.ACTIVE.getStatus());
			userRepository.save(stu);
			return ResponseString.SUCCESS;
		}
	}

	/**
	 * delete a student from DB with the given id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		userRepository.delete(id);
		return ResponseEntity.ok(null);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getOne(@PathVariable Long id) {
		User user = userRepository.findOne(id);
		user.setPwd(null);
		return user;
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		User user1 = userRepository.findByEmail(user.getEmail());
		if (user1 != null) {
			user1.setFirstName(user.getFirstName());
			user1.setLastName(user.getLastName());
			user1.setMobile(user.getMobile());
		}
		userRepository.save(user1);
		return user1;
	}

}
