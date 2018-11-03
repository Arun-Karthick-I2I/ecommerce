package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Product;

public interface ProductDao {
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     * @throws EcommerceException
     */
    List<Product> getProducts() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     * @throws EcommerceException
     */
    Product getById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     * @throws EcommerceException
     */
    Product getByName(String name) throws EcommerceException;
}
