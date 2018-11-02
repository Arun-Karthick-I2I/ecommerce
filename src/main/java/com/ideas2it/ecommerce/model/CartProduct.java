package com.ideas2it.ecommerce.model;

/**
 * <p>
 * Holds the various Products along with their respective prices and quantity
 * required by the Customer who wishes to place the Order
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class CartProduct {
	Integer id;
	Integer quantity;
	Double price;
	Customer customer;
	WarehouseProduct warehouseProduct;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public WarehouseProduct getWarehouseProduct() {
		return warehouseProduct;
	}

	public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
		this.warehouseProduct = warehouseProduct;
	}
}
