package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Specifies the various Products to be sold by the Sellers along with their 
 * description and the Category they belong to.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class Product {
	private Integer id;
	private String name;
	private String description;
	private Category category;
	private Double rating;
	private List<WarehouseProduct> warehouseProducts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	public List<WarehouseProduct> getWarehouseProducts() {
		return warehouseProducts;
	}

	public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
		this.warehouseProducts = warehouseProducts;
	}
}
