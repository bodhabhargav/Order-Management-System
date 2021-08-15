package com. nttdata.casestudy.model;

import java.sql.Date;
import java.util.Objects;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String address;
	private Date dob;
	private String phoneNum;
	private int pinCode;
	private String role;
	private boolean authSuccessful;

	public User(String userId, String password, String name, String email,
			String address, Date dob, String phoneNum, int pinCode,
			String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.name=name;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.phoneNum = phoneNum;
		this.pinCode = pinCode;
		this.role = role;
	}



	public User() {
	}



	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userId, other.userId);
	}

	//Getter methods
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public Date getDob() {
		return dob;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public int getPinCode() {
		return pinCode;
	}

	public String getRole() {
		return role;
	}

	//Setter methods

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name=name;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}



	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}



	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAuthSuccessful() {
		return authSuccessful;
	}



	public void setAuthSuccessful(boolean authSuccessful) {
		this.authSuccessful = authSuccessful;
	}




	public boolean isAdminUser() {
		if ("ADMIN".equals(role.toUpperCase()))
			return true;
		return false;
	}
	public boolean isCustomerUser() {
		if ("CUSTOMER".equals(role.toUpperCase()))
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", address=" + address + ", dob=" + dob + ", phoneNum="
				+ phoneNum + ", pinCode=" + pinCode + ", role=" + role + "]";
	}


}
