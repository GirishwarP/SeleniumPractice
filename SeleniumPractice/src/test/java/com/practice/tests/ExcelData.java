package com.practice.tests;

import java.io.Serializable;

public class ExcelData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String email;
	private Integer rowId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Override
	public String toString() {
		return "ExcelData [userName=" + userName + ", password=" + password + ", email=" + email + ", rowId=" + rowId
				+ "]";
	}
}
