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

    /** 
     * <p>
     * Compares whether two addresses are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar address is present
     *         false  When addresses are not similar
     *
     */
    @Override
    public boolean equals(Object address) {
        if (null == address) {
            return Boolean.FALSE;
        }

        if (!(address instanceof Address)) {
            return Boolean.FALSE;
        }

        if (this == (Address) address) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Address) address).id));
    }
    
    /**
     * <p>
     * Overriding the toString Method. When Invoked,it appends the necessary
     * fields of an Address Entity and then returns it.  
     * </p>
     * 
     * @return display
     *         Appends the values of fields in a StringBuilder object
     *         and then returns it as a String.
     *
     */
     @Override
     public String toString() {
        StringBuilder display = new StringBuilder();
            display.append(addressLine).append(", ").append(city).append(", ").
            append(state).append(", ").append(country).append(" - ").
            append(pincode);

        return display.toString();                    
     }
}
