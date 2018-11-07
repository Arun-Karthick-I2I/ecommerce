package com.ideas2it.ecommerce.service.impl;

import java.util.List;

import com.ideas2it.ecommerce.dao.SellerDao;
import com.ideas2it.ecommerce.dao.impl.SellerDaoImpl;
import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Category;
import com.ideas2it.ecommerce.model.Seller;
import com.ideas2it.ecommerce.service.CategoryService;
import com.ideas2it.ecommerce.service.SellerService;

/**
 * <p>
 * The {@code SellerServiceImpl} 
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class SellerServiceImpl implements SellerService {

    private SellerDao sellerDao = new SellerDaoImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean registerSeller(Seller seller) throws 
            EcommerceException {
        return sellerDao.addSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller checkSellerExistence(Seller seller) throws 
            EcommerceException {
        return sellerDao.checkSellerPresence(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller searchSeller(Integer sellerId) throws 
            EcommerceException {
        return sellerDao.searchSeller(sellerId);  
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Seller searchSellerByUserId(Integer userId) throws
            EcommerceException {
        return sellerDao.getSeller(userId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean deleteSeller(Seller seller) throws EcommerceException {    
        return sellerDao.deleteSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Boolean updateSeller(Seller seller) throws EcommerceException {    
        return sellerDao.updateSeller(seller);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Category> getCategories() throws EcommerceException {
        return categoryService.getCategories();
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Category searchCategory(Integer categoryId) throws 
            EcommerceException {
        return categoryService.searchById(categoryId);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getSellersByName(String sellerName) throws
             EcommerceException {
        return sellerDao.getSellersByName(sellerName);
    }    

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Seller> getAllSellers() throws EcommerceException {
        return sellerDao.getAllSellers();
    }    
}
