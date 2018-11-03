package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.exception.EcommerceException;

public interface CategoryDao {

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
     * @param newCategory
     * @return
     * @throws EcommerceException
     */
    Boolean updateCategory(Category newCategory) throws EcommerceException;
    
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
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     * @throws EcommerceException
     */
    Category retrieveByName(String name) throws EcommerceException;
}
