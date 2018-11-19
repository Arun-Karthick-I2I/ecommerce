package com.ideas2it.ecommerce.model;

import java.util.List;

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
	private List<CartItem> cartItems;
	private User user;
	private Boolean isActive;
	
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

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /** 
     * <p>
     * Compares whether two customers are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar customer is present
     *         false  When customers are not similar
     *
     */
    @Override
    public boolean equals(Object customer) {
        if (null == customer) {
            return Boolean.FALSE;
        }

        if (!(customer instanceof Customer)) {
            return Boolean.FALSE;
        }

        if (this == (Customer) customer) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Customer) customer).id));
    }

}
