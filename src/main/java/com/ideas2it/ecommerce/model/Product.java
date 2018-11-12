package com.ideas2it.ecommerce.model;

import java.util.Base64;
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
	private byte[] image;
	private String name;
	private String description;
	private Category category;
	private Float rating;
	private List<WarehouseProduct> warehouseProducts;
	private String base64Image;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public List<WarehouseProduct> getWarehouseProducts() {
		return warehouseProducts;
	}

	public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
		this.warehouseProducts = warehouseProducts;
	}

    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.image);
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
