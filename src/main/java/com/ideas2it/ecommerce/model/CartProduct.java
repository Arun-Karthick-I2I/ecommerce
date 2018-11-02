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
	private Integer id;
	private Integer quantity;
	private Float price;
	private Customer customer;
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
