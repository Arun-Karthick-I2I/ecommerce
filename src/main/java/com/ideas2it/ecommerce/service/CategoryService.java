package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.exception.EcommerceException;

public interface CategoryService {
	
	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
    List<Category> getCategories() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     */
    Boolean insertCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Boolean deleteCategory(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     */
    Boolean updateCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Category searchById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    Category searchByName(String name) throws EcommerceException;
}
