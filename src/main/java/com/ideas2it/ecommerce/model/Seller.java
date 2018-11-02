package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Seller contains the contact details of the seller, list of warehouse
 * addresses, list of products they offer to customers and login credentials.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class Seller {
    private Integer id;
    private String name;
    private String mobileNumber;
    private String emailId;
    private Float rating;
    private List<WarehouseProduct> warehouseProducts;
    private List<Address> addresses;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
