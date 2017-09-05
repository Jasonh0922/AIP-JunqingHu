package com.bit.pms.enums;

public enum UserRole {
	ADMIN(0), STUDENT(1);

	private int role;

	UserRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
