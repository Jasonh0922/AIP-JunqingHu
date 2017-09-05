package com.bit.pms.repository;


import com.bit.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPwd(String email, String pwd);

	User findByEmail(String email);

	List<User> findByFirstNameContaining(String key);
}
