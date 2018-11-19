package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Warehouse Product represents the product a seller has in his warehouse. Many
 * sellers can sell the same product on the store but each of them will sell it
 * at a different price and will have different quantity in their warehouse. It
 * contains the seller details, price, quantity and other details of the
 * particular product. It contains the list of order items in which that
 * particular product is purchased.
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
    private List<OrderItem> orderItems;

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    /** 
     * <p>
     * Compares whether two warehouse products are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar warehouse product is present
     *         false  When warehouse products are not similar
     *
     */
    @Override
    public boolean equals(Object warehouseProduct) {
        if (null == warehouseProduct) {
            return Boolean.FALSE;
        }

        if (!(warehouseProduct instanceof WarehouseProduct)) {
            return Boolean.FALSE;
        }

        if (this == (WarehouseProduct) warehouseProduct) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((WarehouseProduct) warehouseProduct).id));
    }
    
}
