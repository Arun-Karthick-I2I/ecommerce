package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.exception.EcommerceException;

public interface CategoryDao {
	/**
     * <p>
     * Used to display the details of all the Categories in the List
     * </p>
     *
     * @return Returns the details of all the Categories. Otherwise, 
     *         returns an empty String object.
     */
    List<Category> getCategories() throws EcommerceException;
}
