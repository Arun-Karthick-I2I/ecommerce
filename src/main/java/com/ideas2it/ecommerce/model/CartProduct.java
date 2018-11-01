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

	private Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	private Integer getQuantity() {
		return quantity;
	}

	private void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	private Double getPrice() {
		return price;
	}

	private void setPrice(Double price) {
		this.price = price;
	}

	private Customer getCustomer() {
		return customer;
	}

	private void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private WarehouseProduct getWarehouseProduct() {
		return warehouseProduct;
	}

	private void setWarehouseProduct(WarehouseProduct warehouseProduct) {
		this.warehouseProduct = warehouseProduct;
	}
}
