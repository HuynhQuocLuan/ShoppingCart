package com.shoppingcart.model;

public class AccountInfo {
	
	private String name;
	
	private String userName;
	
	private String password;
	
	private boolean active = false;
	
	private String userRole;

	public AccountInfo() {
		super();
	}
	
	public AccountInfo(String name, String userName, String password, boolean active, String userRole) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.userRole = userRole;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	

}
