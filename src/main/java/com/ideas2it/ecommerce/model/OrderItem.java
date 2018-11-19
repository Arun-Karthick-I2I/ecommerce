package com.ideas2it.ecommerce.model;

import com.ideas2it.ecommerce.common.enums.ORDER_STATUS;

/**
 * <p>
 * OrderItem indicates the Order placed to purchase an individual Product along
 * with its respective price, the quantity required by the Customer and the 
 * status of the Order whether it has been ordered, delivered, cancelled or 
 * Returned.  
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class OrderItem {
    private Integer id;
    private Integer quantity;
    private Float price;
    private ORDER_STATUS status;
    private Order order;
    private WarehouseProduct warehouseProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public WarehouseProduct getWarehouseProduct() {
        return warehouseProduct;
    }

    public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
        this.warehouseProduct = warehouseProduct;
    }
    
    /** 
     * <p>
     * Compares whether two Order Items are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar Order Item is present
     *         false  When Order Items are not similar
     *
     */
    @Override
    public boolean equals(Object orderItem) {
        if (null == orderItem) {
            return Boolean.FALSE;
        }

        if (!(orderItem instanceof OrderItem)) {
            return Boolean.FALSE;
        }

        if (this == (OrderItem) orderItem) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((OrderItem) orderItem).id));
    }
}
