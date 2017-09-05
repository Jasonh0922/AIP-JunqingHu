package com.bit.pms.enums;

public enum UserStatus {
	PENDING("1"), ACTIVE("2"), ANCLED("3");

	private String status;

	private UserStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
