package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Seller represents a company who wishes to sell products online. It contains
 * the list of warehouse products which he sells. The warehouse product model
 * contains the details of a product in his warehouse. Seller also contains the
 * contact details and his warehouse addresses.
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
