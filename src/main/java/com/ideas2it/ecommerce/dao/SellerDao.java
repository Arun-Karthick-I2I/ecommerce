package com.ideas2it.ecommerce.dao;

import java.util.List;

import com.ideas2it.ecommerce.exception.EcommerceException;
import com.ideas2it.ecommerce.model.Seller;

/**
 * <p>
 * The {@code SellerDao} interface provides seller related operations that can
 * be performed to a e-commerce Store. It provides the basic operations such as
 * add, remove and find sellers from the store.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public interface SellerDao {

    Boolean addSeller(Seller seller) throws EcommerceException;
    
    Boolean deleteSeller(Seller seller) throws EcommerceException;
    
    Boolean updateSeller(Seller seller) throws EcommerceException;
    
    Seller checkSellerPresence(Seller seller) throws EcommerceException;
    
    Seller getSeller(Integer userId) throws EcommerceException;
    
    Seller searchSeller(Integer sellerId) throws EcommerceException;
    
    List<Seller> getSellersByName(String sellerName) throws EcommerceException;
    
    List<Seller> getAllSellers() throws EcommerceException;
    
}
