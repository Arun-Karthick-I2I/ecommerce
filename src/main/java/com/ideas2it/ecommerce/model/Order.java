package com.ideas2it.ecommerce.model;

import java.sql.Date;
import java.util.List;


/**
 * <p>
 * Order contains customer details, product, delivery address, quantity and
 * price of the product at the time of purchase.
 * </p>
 * 
 * @author Anantharaj.S
 */
public class Order {
    private Integer id;
    private Customer customer;
    private Date orderDate;
    private String modeOfPayment;
    private Float price;
    private Address address;
    private List<OrderItem> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    /** 
     * <p>
     * Compares whether two orders are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar order is present
     *         false  When orders are not similar
     *
     */
    @Override
    public boolean equals(Object order) {
        if (null == order) {
            return Boolean.FALSE;
        }

        if (!(order instanceof Order)) {
            return Boolean.FALSE;
        }

        if (this == (Order) order) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Order) order).id));
    }

}
