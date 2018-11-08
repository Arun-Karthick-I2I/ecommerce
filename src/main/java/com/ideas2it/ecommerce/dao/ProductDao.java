package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Product;

/**
 * <p>
 * This interface provides basic functionalities such as get all the
 * available Products and fetch Product by using ID or Name specified. 
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public interface ProductDao {
    
    /**
     * <p>
     * Used to fetch the details of all the available Products.
     * </p>
     * 
     * @return  Returns the list of Products available. Otherwise,
     *          returns an empty Object.
     */
    List<Product> getProducts() throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of the Product for the ID specified.
     * </p>
     * 
     * @param   id  ID of the Product whose details are to fetched.
     * @return      Returns the Product for the ID specified. Otherwise,
     *              returns an empty object.
     */
    Product getById(Integer id) throws EcommerceException;
    
    /**
     * <p>
     * Used to fetch the details of the Product based on the name specified. 
     * </p>
     * 
     * @param   name  Name of the Product whose details are to fetched
     * @return        Returns the Product for the name specified. 
     *                Otherwise, returns an empty object.
     */
    Product getByName(String name) throws EcommerceException;
}
