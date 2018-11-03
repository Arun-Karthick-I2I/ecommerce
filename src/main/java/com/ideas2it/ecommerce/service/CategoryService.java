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
	 * @throws EcommerceException
	 */
    List<Category> getCategories() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     * @throws EcommerceException
     */
    Boolean insertCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     * @throws EcommerceException
     */
    Boolean deleteCategory(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param category
     * @return
     * @throws EcommerceException
     */
    Boolean updateCategory(Category category) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     * @throws EcommerceException
     */
    Category retrieveById(Integer id) throws EcommerceException;
}
