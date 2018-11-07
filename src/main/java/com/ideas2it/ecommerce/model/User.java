package com.ideas2it.ecommerce.model;

import com.ideas2it.ecommerce.common.enums.Role.USER_ROLES;

/**
 * <p>
 * User contains role of the user such as customer, seller, admin and their
 * login credentials
 * </p>
 * 
 * @author Anantharaj.S
 * 
 */
public class User {

	private Integer id;
	private String userName;
	private String password;
	private USER_ROLES role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public USER_ROLES getRole() {
		return role;
	}

	public void setRole(USER_ROLES role) {
		this.role = role;
	}

}
