package com.bit.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.pms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPwd(String email, String pwd);

	User findByEmail(String email);

	List<User> findByFirstNameContaining(String key);

	@Query("select u from User u join u.roles r where r != 'ROLE_ADMIN'")
	List<User> findAllUser();

	@Query("update User user set user.pwd = :newPwd where user.email = :email")
	@Modifying
	void updatePwdByEmail(@Param("email") String email, @Param("newPwd") String newPwd);

}
