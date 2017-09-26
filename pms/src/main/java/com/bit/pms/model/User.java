package com.bit.pms.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "TUSER")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", length = 64)
	private String firstName;

	@Column(name = "last_name", length = 64)
	private String lastName;

	@Email
	@NotNull
	@Column(name="email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "pwd", length = 1000)
	private String pwd;

	private int role;

	private String status;

	@Transient
	private String name;

	public User() {

	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.mobile = user.getMobile();
		this.pwd = user.getPwd();
		this.name = user.getName();
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

	public String getName() {
		return firstName + " " + lastName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
