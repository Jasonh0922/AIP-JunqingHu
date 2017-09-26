package com.bit.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bit.pms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPwd(String email, String pwd);

	User findByEmail(String email);

	List<User> findByFirstNameContaining(String key);

	@Query("select u from User u join u.roles r where r != 'ROLE_ADMIN'")
	List<User> findAllUser();
}
