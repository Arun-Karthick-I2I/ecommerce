package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.ProductDao;
import com.ideas2it.ecommerce.dao.impl.ProductDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Product;
import com.ideas2it.ecommerce.service.ProductService;

/**
 * <p>
 * This class provides basic functionalities such as get all the
 * available Products and fetch Product by using ID or Name specified.
 * </p>
 * 
 * @author Pavithra.S
 *
 */
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    
    /**
     * {@inheritDoc}
     */
    public List<Product> getProducts() throws EcommerceException { 
        return productDao.getProducts(); 
    }
    
    /**
     * {@inheritDoc}
     */
    public Product searchById(Integer id) throws EcommerceException {
        return productDao.getById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    public Product searchByName(String name) throws EcommerceException {
        return productDao.getByName(name);
    }
}
