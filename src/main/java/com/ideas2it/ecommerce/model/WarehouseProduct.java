package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Warehouse Product contains the seller details, price, quantity and other
 * details of the particular product. It contains the list of orders in which
 * that particular product is placed.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class WarehouseProduct {
    private Integer id;
    private Seller seller;
    private Product product;
    private Integer quantity;
    private Float price;
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
