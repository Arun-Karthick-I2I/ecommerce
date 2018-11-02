package com.ideas2it.ecommerce.model;

/**
 * <p>
 * Order contains customer details, product, delivery address, quantity and price
 * of the product at the time of purchase.
 * </p>
 * 
 * @author Anantharaj.S
 * 
 */
public class Order {

	private Integer id;
	private Customer customer;
	private WarehouseProduct warehouseProduct;
	private Integer quantity;
	private Float price;
	private Address address;

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

	public WarehouseProduct getWarehouseProduct() {
		return warehouseProduct;
	}

	public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
		this.warehouseProduct = warehouseProduct;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
