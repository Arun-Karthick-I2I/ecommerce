package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.exception.EcommerceException;

public interface CategoryService {
	
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
