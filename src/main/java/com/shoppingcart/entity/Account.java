package com.shoppingcart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_MANAGER = "MANAGER";
	public static final String ROLE_EMPLOYEE = "EMPLOYEE";
	
	@Id
	@Column(name = "User_Name", length = 20, nullable = false)
	private String userName;
	
	@Column(name = "Name", length = 225, nullable = false)
	private String name;
	
	@Column(name = "Password", length = 20, nullable = false)
	private String password;
	
	@Column(name = "Active", length = 1, nullable = false)
	private boolean active = true;
	
	@Column(name = "User_Role", length = 20, nullable = false)
	private String userRole;

	public Account() {
		super();
	}

	public Account(String userName, String password, boolean active, String userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.userRole = userRole;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
