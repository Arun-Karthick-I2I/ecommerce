package com.ideas2it.ecommerce.model;

/**
 * <p>
 * Uesr contains role of the user such as customer, seller, admin and their
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
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
