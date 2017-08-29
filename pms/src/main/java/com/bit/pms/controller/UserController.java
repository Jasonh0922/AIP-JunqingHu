package com.bit.pms.controller;

import com.bit.pms.constants.ResponseString;
import com.bit.pms.enums.UserStatus;

import com.bit.pms.model.User;
import com.bit.pms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/users/")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value="", method = RequestMethod.GET)
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String addStudent(@RequestBody User stu){
		stu.setRole(new Integer(1));
		stu.setStatus(UserStatus.PENDING.getStatus());
		userRepository.save(stu);
		return ResponseString.SUCCESS;
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public String delete(@RequestBody User stu){
		userRepository.delete(stu.getId());
		return ResponseString.SUCCESS;
	}

}
