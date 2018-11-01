package com.ideas2it.ecommerce.model;

import java.util.List;

import com.ideas2it.ecommerce.model.Address;
import com.ideas2it.ecommerce.model.CartProduct;
import com.ideas2it.ecommerce.model.Order;

/**
 * <p>
 * Customer contains personal details, order details, delivery address details, 
 * cart product and user login details.
 * </p>
 * 
 * @author Anantharaj.S
 * 
 */
public class Customer {

	private Integer id;
	private String name;
	private String mobileNumber;
	private String emailId;
	private List<Order> orders;
	private List<Address> addresses;
	private List<CartProduct> cartProducts;
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
