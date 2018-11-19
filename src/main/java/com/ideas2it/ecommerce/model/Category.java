package com.ideas2it.ecommerce.model;

import java.util.List;

/**
 * <p>
 * Category specifies the various types of the Product such as mobile, camera,
 * laptops, etc.. Each Product belongs to one of the Categories enlisted.
 * Category helps us in searching a Product quickly and easily.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class Category implements Comparable<Category> {
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
	
	public int compareTo(Category category) {
        return this.id - category.id; 
    } 
	
    /** 
     * <p>
     * Compares whether two categories are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar category is present
     *         false  When categories are not similar
     *
     */
    @Override
    public boolean equals(Object category) {
        if (null == category) {
            return Boolean.FALSE;
        }

        if (!(category instanceof Category)) {
            return Boolean.FALSE;
        }

        if (this == (Category) category) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Category) category).id));
    }

}
