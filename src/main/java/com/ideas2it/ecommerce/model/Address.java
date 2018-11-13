package com.ideas2it.ecommerce.model;

/**
 * <p>
 * Address either contains the customer address details such as street, city,
 * state, country and pincode that are necessary for delivering a product or
 * contains the warehouse address from where the product dispatches.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class Address {
    private Integer id;
    private User user;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String pincode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
