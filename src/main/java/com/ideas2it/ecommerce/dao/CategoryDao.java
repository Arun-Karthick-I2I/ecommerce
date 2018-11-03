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
     * @param newCategory
     * @return
     */
    Boolean updateCategory(Category newCategory) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Category getById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    Category getByName(String name) throws EcommerceException;
}
