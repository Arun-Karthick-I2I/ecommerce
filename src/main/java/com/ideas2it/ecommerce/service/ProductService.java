package com.ideas2it.ecommerce.service;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Product;

public interface ProductService {
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    List<Product> getProducts() throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    Product searchById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * 
     * </p>
     * 
     * @param name
     * @return
     */
    Product searchByName(String name) throws EcommerceException;
}
