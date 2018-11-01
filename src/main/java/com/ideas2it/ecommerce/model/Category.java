package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Category specifies the various types of the Product such as mobile, camera,
 * pendrive, etc..
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class Category {
	private Integer id;
	private String name;
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
