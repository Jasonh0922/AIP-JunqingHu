package com.bit.pms.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "TUSER")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", length = 64)
	private String firstName;

	@Column(name = "last_name", length = 64)
	private String lastName;

	@Email
	@NotNull
	private String email;

	private String mobile;

	private String pwd;

	@Column(name = "status", length = 1)
	private String status;

	@Transient
	private String name;

	@Column(name = "role")
	private Integer role;

	public User() {

	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.mobile = user.getMobile();
		this.pwd = user.getPwd();
		this.status = user.getStatus();
		this.name = user.getName();
		this.role = user.getRole();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getName() {
		return firstName + " " + lastName;
	}
}
